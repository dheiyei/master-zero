package com.lhj.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownloadUtil {
    public void downloadExcel(HttpServletResponse response , HSSFWorkbook workbook, String fileName) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        workbook.write(os);
        os.flush();
        os.close();
    }

}
