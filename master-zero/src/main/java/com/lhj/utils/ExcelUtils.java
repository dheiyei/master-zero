package com.lhj.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {

    /**
     * 小品种油报表
     * @param data 数据
     * @param templatePath 魔板路径
     * @param sheetName 表一名称
     * @param keyList 对应的key
     * @param excelPath 导出路径
     * @return 0：准备 1：结束  -1：异常
     */
    public void excelExport(JSONArray data , String templatePath, String sheetName, String[] keyList, String excelPath) throws IOException {


        //获取模板
        POIFSFileSystem fsPoi=new POIFSFileSystem(new FileInputStream(templatePath));
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook(fsPoi);
        //创建工作表对象
        HSSFSheet sheet =workbook.getSheet(sheetName);
        for (int i = 0 ;i < data.size() ; i ++){
            //从第6行开始
            HSSFRow row = sheet.createRow(i+5);
            //第一个单元格
            JSONObject jsonObject = data.getJSONObject(i);
            HSSFCell cell=row.createCell(0);
            cell.setCellValue(i+1);//序号
            for (int j = 0 ; j < keyList.length ; j++){
                cell=row.createCell(j+1);
                cell.setCellValue(jsonObject.get(keyList[j]).toString());
            }
        }
        FileOutputStream outFile = new FileOutputStream(excelPath+"/表格" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +".xls");
        workbook.write(outFile);
    }

}
