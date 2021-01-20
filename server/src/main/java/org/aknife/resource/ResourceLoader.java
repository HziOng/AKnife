package org.aknife.resource;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.aknife.resource.model.Location;
import org.aknife.resource.model.MapResource;
import org.aknife.resource.model.NpcCharacter;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * 配置资源加载器，用于加载地图信息、NPC信息、活动信息等
 * @ClassName ResourceLoader
 * @Author HeZiLong
 * @Data 2021/1/18 10:08
 */
@Log4j
public class ResourceLoader {

    /**
     * 所有xlsx文件的基础路径
     */
    private static final String BASE_PATH = "xlsx";

    /**
     * 地图配置文件名字
     */
    private static final String MAP_RESOURCE = "map.xlsx";

    /**
     * npc配置文件名字
     */
    private static final String NPC_RESOURCE = "npc.xlsx";

    /**
     * 地形地图
     */
    private static HashMap<Integer, MapResource> terrainMap = null;



    /**
     * 加载地图配置文件
     * @return
     */
    public static HashMap loadMapInfo(){
        terrainMap = new HashMap<>();
        try {
            String filePath = ResourceLoader.class.getClassLoader().getResource(BASE_PATH+"/"+MAP_RESOURCE).getPath();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = wb.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();

            Row row = null;
            Cell cell = null;
            for (int i = firstRowNum+1; i <= lastRowNum; i++) {
                //取得第i行 （从第二行开始取，因为第一行是表头）
                row = sheet.getRow(i);
                MapResource map = new MapResource();
                if((cell = row.getCell(0)) != null) {
                    map.setId((int) cell.getNumericCellValue());
                }
                if((cell = row.getCell(1)) != null) {
                    map.setName(cell.getStringCellValue());
                }
                if((cell = row.getCell(2)) != null) {
                    map.setNumberLimit((int) cell.getNumericCellValue());
                }
                if((cell = row.getCell(3)) != null) {
                    String npcIds = cell.getStringCellValue();
                }
                terrainMap.put(map.getId(), map);
            }
        } catch (FileNotFoundException e) {
            log.info("地图配置加载失败");
            e.printStackTrace();
        } finally {
            return terrainMap;
        }
    }


    /**
     * 加载npc配置文件,这里会直接将npc信息放置到地图数据对象中
     */
    public static void loadNpcInfo(){
        try {
            String filePath = ResourceLoader.class.getClassLoader().getResource(BASE_PATH+"/"+NPC_RESOURCE).getPath();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = wb.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();

            Row row = null;
            Cell cell = null;
            for (int i = firstRowNum+1; i <= lastRowNum; i++) {
                //取得第i行 （从第二行开始取，因为第一行是表头）
                row = sheet.getRow(i);
                NpcCharacter character = new NpcCharacter();
                if((cell = row.getCell(0)) != null) {
                    character.setId((int) cell.getNumericCellValue());
                }
                if((cell = row.getCell(1)) != null) {
                    character.setName(cell.getStringCellValue());
                }
                if((cell = row.getCell(2)) != null) {
                    String monologue = cell.getStringCellValue();
                    character.setMonologue(JSONObject.parseObject(monologue, HashMap.class));
                }
                if((cell = row.getCell(3)) != null) {
                    character.setMapId((int) cell.getNumericCellValue());
                }
                if((cell = row.getCell(4)) != null) {
                    character.setLocation(JSONObject.parseObject(cell.getStringCellValue(), Location.class));
                }
                terrainMap.get(character.getMapId()).getNpc().put(character.getId(), character);
            }
        } catch (FileNotFoundException e) {
            log.info("NPC配置加载失败");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResourceLoader.loadMapInfo();
        ResourceLoader.loadNpcInfo();
        for (Integer key : ResourceLoader.terrainMap.keySet()){
            MapResource map = terrainMap.get(key);
            System.out.println(map);
        }
    }
}
