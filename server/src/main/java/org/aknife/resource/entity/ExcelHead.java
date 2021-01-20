package org.aknife.resource.entity;

import lombok.Data;

/**
 * @ClassName ExcelHead
 * @Author HeZiLong
 * @Data 2021/1/20 17:29
 */
@Data
public class ExcelHead {
    /**
     * Excel名
     */
    private String excelName;
    /**
     * 实体类属性名
     */
    private String entityName;
    /**
     * 必填值（就是是否可以为空）
     */
    private boolean required=false;


    public ExcelHead(String excelName, String entityName, boolean required) {
        this.excelName = excelName;
        this.entityName = entityName;
        this.required = required;
    }

    public ExcelHead(String excelName, String entityName) {
        this.excelName = excelName;
        this.entityName = entityName;
    }

    public ExcelHead(){};
}