package com.koke.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReadExcel {
	HSSFFormulaEvaluator hssfFormulaEvaluator = null;
	XSSFFormulaEvaluator xssfFormulaEvaluator = null;

	/**
	 * 读取excel中的数据
	 * @return List<StudentBean>
	 * @author zhang 2015-08-18 00:08
	 */
	public List<Map<String,String>> readExcel(MultipartFile file,String[] keys) {

		if (file != null && !file.getName().equals("")) {
			String ext = getExt(file.getOriginalFilename());
			if (ext!=null && !ext.equals("")) {
				if (ext.equals("xls")) {
					return readXls(file,keys);
				} else if (ext.equals("xlsx")) {
					return readXlsx(file,keys);
				}
			}
		}
		return new ArrayList<>();
	}

	/**
	 * 读取后缀为xls的excel文件的数据
	 * @return List<StudentBean>
	 * @author zhang 2015-08-18 00:10
	 */
	private List<Map<String,String>> readXls(MultipartFile file,String[] keys) {

		HSSFWorkbook hssfWorkbook = null;
		try {
			InputStream is = file.getInputStream();
			hssfWorkbook = new HSSFWorkbook(is);
			new HSSFFormulaEvaluator(hssfWorkbook);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String,String>> list = new ArrayList<>();
		if (hssfWorkbook != null) {
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						Map<String, String> map = new HashMap<>();
						for (int i = 0; i < keys.length; i++) {
							map.put(keys[i], getValue(hssfRow.getCell(i)).trim());
						}
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 读取后缀为xlsx的excel文件的数据
	 * @return List<StudentBean>
	 * @author zhang 2015-08-18 00:08
	 */
	private List<Map<String,String>> readXlsx(MultipartFile file,String[] keys) {

		XSSFWorkbook xssfWorkbook = null;
		try {
			InputStream is = file.getInputStream();
			xssfWorkbook = new XSSFWorkbook(is);
			xssfFormulaEvaluator = new XSSFFormulaEvaluator(xssfWorkbook);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String,String>> list = new ArrayList<>();
		if(xssfWorkbook!=null){
			// Read the Sheet
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						Map<String,String> studentBean = new HashMap<>();
						for (int i = 0; i < keys.length; i++) {
							XSSFCell cell = xssfRow.getCell(i);
							studentBean.put(keys[i], getValue(cell).trim());
						}
						list.add(studentBean);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获取文件扩展名
	 * @return String
	 * @author zhang 2015-08-17 23:26
	 */
	private String getExt(String path) {
		if (path == null || path.equals("") || !path.contains(".")) {
			return null;
		} else {
			return path.substring(path.lastIndexOf(".") + 1);
		}
	}


	/**
	 * 判断后缀为xlsx的excel文件的数据类型
	 * @return String
	 * @author zhang 2015-08-18 00:12
	 */
	private String getValue(XSSFCell xssfCell) {
		if (xssfCell != null) {
			if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfCell.getBooleanCellValue());
			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
				if(HSSFDateUtil.isCellDateFormatted(xssfCell)){//用于转化为日期格式
					Date d = xssfCell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return formater.format(d);
				}else{
					DecimalFormat df = new DecimalFormat("0.0000");
					return df.format(xssfCell.getNumericCellValue());
				}
			}else if (xssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {//有公式的Excel单元格
				//这样对于字符串cell.getStringCellValue()方法即可取得其值，如果公式生成的是数值，使用cell.getStringCellValue()方法会抛出IllegalStateException异常，在异常处理中使用cell.getNumericCellValue();即可。
				try {
					return String.valueOf(xssfCell.getStringCellValue());
				} catch (IllegalStateException e) {
					CellValue tempCellValue = xssfFormulaEvaluator.evaluate(xssfCell);
					double iCellValue = tempCellValue.getNumberValue();
					return String.valueOf(iCellValue);
				}
			} else {
				return String.valueOf(xssfCell.getStringCellValue());
			}
		}else{
			return "";
		}
	}

	/**
	 * 判断后缀为xls的excel文件的数据类型
	 * @return String
	 * @author zhang 2015-08-18 00:12
	 */
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell != null) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				DecimalFormat df = new DecimalFormat("0.0000");
				return df.format(hssfCell.getNumericCellValue());
			} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {//有公式的Excel单元格
				//这样对于字符串cell.getStringCellValue()方法即可取得其值，如果公式生成的是数值，使用cell.getStringCellValue()方法会抛出IllegalStateException异常，在异常处理中使用cell.getNumericCellValue();即可。
				try {
					return String.valueOf(hssfCell.getStringCellValue());
				} catch (IllegalStateException e) {
					CellValue tempCellValue = hssfFormulaEvaluator.evaluate(hssfCell);
					double iCellValue = tempCellValue.getNumberValue();
					return String.valueOf(iCellValue);
				}
			} else {
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}else{
			return "";
		}

	}


}
