package com.hui.base.common.autoreport.util;

import com.hui.base.common.autoreport.model.ExcelData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

/**
 * <b><code>ExcelGenerator</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/6 1:05.
 *
 * @author HuWeihui
 */
public class ExcelGenerator {
    /**
     * 生成XLS版的EXCEL.
     *
     * @param fileName  the file name
     * @param sheetName the sheet name
     * @param excelData the excel data
     * @return the hssf workbook
     * @author HuWeihui
     * @since hui_project v1
     */
    public static HSSFWorkbook createXLSWorkBook(String fileName, String sheetName, ExcelData excelData){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        if (StringUtils.isEmpty(sheetName)){
            sheetName = "data";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        List<String> title = excelData.getTitle();
        List<List<String>> values = excelData.getValues();
        //创建标题
        for (int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return workbook;
    }

    /**
     * 生成xlsx版的EXCEL.
     *
     * @param fileName  the file name
     * @param sheetName the sheet name
     * @param excelData the excel data
     * @return the sxssf workbook
     * @author HuWeihui
     * @since hui_project v1
     */
    public static SXSSFWorkbook createXLSXWorkBook(String fileName, String sheetName, ExcelData excelData){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        SXSSFSheet sheet = workbook.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        SXSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        SXSSFCell cell = null;

        List<String> title = excelData.getTitle();

        List<List<String>> values = excelData.getValues();
        //创建标题
        for (int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        //创建内容
        for (int i = 0; i < values.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return workbook;
    }
}
