package org.aknife.resource.util;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.resource.annotation.ExcelCell;
import org.aknife.resource.model.IResource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Author HeZiLong
 * @Data 2021/1/20 14:46
 */
public class ExcelUtil {


    public static Workbook readFile(File file) throws GlobalException {
        try {
            //xls和xlsx必须不同的处理类，POI就这么规定的
            if (file.getName().toLowerCase().endsWith(".xls")) {
                return readFileHSSF(new FileInputStream(file));
            } else {
                return readFileXSSF(new FileInputStream(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 读取xls格式文件流，HSSF*是处理xls格式的，XSSF是处理xlsx格式文件
     * @param stream
     * @return
     * @throws GlobalException
     * @throws IOException
     */
    private static Workbook readFileHSSF(InputStream stream) throws GlobalException, IOException {
        try {
            return new HSSFWorkbook(stream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        } finally {
            stream.close();
        }
    }

    /**
     * 读取xlsx格式文件流
     * @param stream
     * @return
     * @throws GlobalException
     * @throws IOException
     */
    private static Workbook readFileXSSF(InputStream stream) throws GlobalException, IOException {
        try {
            return new XSSFWorkbook(stream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        } finally {
            stream.close();
        }
    }

    /**
     * 读取路径为path的文件流
     * @param path
     * @return
     * @throws GlobalException
     */
    public static Workbook readFile(String path) throws GlobalException {
        path = ExcelUtil.class.getClassLoader().getResource(path).getPath();
        File file = new File(path);
        if (!file.exists()) {
            throw new GlobalException("文件不存在");
        }
        if (!file.isFile()) {
            throw new GlobalException("不是合法的文件");
        }
        return readFile(file);
    }

    public static Sheet readSheet(HSSFWorkbook workbook, Integer index) {
        return workbook.getSheetAt(index);
    }

    public static Object[] convertArrayByRow(Row row) {
        int cols = row.getLastCellNum();
        Object[] arr = new Object[cols];
        for (int i = 0; i < cols; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            if (cell.getCellTypeEnum() == CellType.STRING) {
                arr[i] = cell.getStringCellValue();
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                arr[i] = cell.getNumericCellValue();
            } else {

            }
        }
        return arr;
    }

    public static <T extends Object> T convertBeanFromArray(Object[] arr, Class<T> clazz) throws GlobalException {
        T entity;
        try {
            entity = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(ExcelCell.class)) {
                    continue;
                }

                field.setAccessible(true);
                ExcelCell anno = field.getAnnotation(ExcelCell.class);
                Class<?> cellType = anno.Type();
                Integer col = anno.col();

                if (col >= arr.length) {
                    continue;
                }
                if (arr[col] == null) {
                    continue;
                }
                if (cellType == null) {
                    field.set(entity, arr[col]);
                } else {
                    field.set(entity, numericByStr(cellType, arr[col]));
                }
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    public static <T extends Object> Object numericByStr(Class<T> clazz, Object param) {
        if (param == null) {
            return null;
        }
        String arg = String.valueOf(param);
        if (clazz.isAssignableFrom(Double.class)) {
            return Double.valueOf(arg);
        } else if (clazz.isAssignableFrom(Long.class)) {
            Double d = Double.valueOf(arg);
            return d.longValue();
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return Double.valueOf(arg).intValue();
        } else {
            return arg;
        }
    }

    public static <K extends Serializable,V extends IResource> HashMap<K, V> getBeanMappingID(String path, Class clazz){
        HashMap<K, V> map = new HashMap<>();
        Workbook book = readFile(path);
        for (int i = 1; i <= book.getSheetAt(0).getLastRowNum(); i++) {
            Object[] arr = convertArrayByRow(book.getSheetAt(0).getRow(i));
            V value = (V) convertBeanFromArray(arr, clazz);
            Cell cell = book.getSheetAt(0).getRow(i).getCell(0);
            if (cell.getCellTypeEnum() == CellType.STRING) {
                map.put((K) cell.getStringCellValue(), value);
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                map.put((K) new Integer(new Double(cell.getNumericCellValue()).intValue()), value);
            } else {

            }
            value.init();
        }
        return map;
    }

    public static <T> List<T> getBean(String path, Class<T> clazz) throws GlobalException {
        List<T> list = new ArrayList<T>();
        Workbook book = readFile(path);
        for (int i = 1; i <= book.getSheetAt(0).getLastRowNum(); i++) {
            Object[] arr = convertArrayByRow(book.getSheetAt(0).getRow(i));
            T t = convertBeanFromArray(arr, clazz);
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> getBean(File file, Class<T> clazz) throws GlobalException {
        List<T> list = new ArrayList<T>();
        Workbook book = readFile(file);
        for (int i = 1; i <= book.getSheetAt(0).getLastRowNum(); i++) {
            Object[] arr = convertArrayByRow(book.getSheetAt(0).getRow(i));
            T t = convertBeanFromArray(arr, clazz);
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> getBean(InputStream stream, String excelType, Class<T> clazz)
            throws GlobalException, IOException {
        Workbook book;
        if (excelType.equals("xls")) {
            book = readFileHSSF(stream);
        } else {
            book = readFileXSSF(stream);
        }
        List<T> list = new ArrayList<T>();
        for (int i = 1; i <= book.getSheetAt(0).getLastRowNum(); i++) {
            Object[] arr = convertArrayByRow(book.getSheetAt(0).getRow(i));
            T t = convertBeanFromArray(arr, clazz);
            list.add(t);
        }
        return list;
    }
}
