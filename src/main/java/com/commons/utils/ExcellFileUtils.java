package com.commons.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcellFileUtils implements AbstractExcellFileCreator {
	private static final transient Logger logger = Logger.getLogger(ExcellFileUtils.class);
	private static final String seperator = "&";
	public static final String CUSTOM_CELL = "CUSTOM";

	@Override
	public HSSFWorkbook createExcellWorkBook() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		return workbook;
	}

	@Override
	public int createExcellHeader(ExcelInfo excelInfo, HSSFSheet sheet, int rowNum) {
		List<String> headerDetails = excelInfo.getHeaderDetails();
		if (headerDetails != null && !headerDetails.isEmpty()) {
			for (String header : headerDetails) {
				int cellnum = 0;
				Row row = sheet.createRow(rowNum);
				if (header != null && !header.isEmpty()) {
					String[] cellData = header.split(seperator);
					for (String cellVal : cellData) {
						Cell cell = row.createCell(cellnum++);
						if (header instanceof String) {
							cell.setCellValue(cellVal);
						}
					}
				}
				rowNum = rowNum + 1;
			}
		}
		return rowNum;
	}

	@Override
	public void createExcellRowsData(ExcelInfo excelInfo, HSSFSheet sheet, int rowNum) {
		List<Object> rowData = excelInfo.getRowDetails();
		for (Object data : rowData) {
			Row row = sheet.createRow(rowNum);
			setBeanToCell(data, excelInfo.getIncludeRowData(), row);
			rowNum=rowNum +1;
		}
	}

	public void getFileOutPutStream(String fileName, Workbook workbook) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			logger.info("File written sucessfully with the name of:" + fileName);
		} catch (FileNotFoundException ex1) {
			logger.error("No such a file in specified location:" + fileName);
		} catch (IOException ex2) {
			logger.error("I/O issue while read/write:" + fileName);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("Error while closing getFileOutPutStream");
				}
			}
		}
	}

	private void setBeanToCell(Object object, List<String> inclusion, Row row) {
		if (object == null) {
			logger.error("\n Null data is not able to process");
			return;
		}
		try {
			if (inclusion != null && !inclusion.isEmpty()) {
				for (String fieldName : inclusion) {
					Cell cell = row.createCell(inclusion.indexOf(fieldName));
					Field field = getMatchedFieldData(fieldName, object);
					if (field != null) {
						Object fieldObjects = field.get(object);
						setCellValue(fieldObjects, cell);
					}
				}
			}
			logger.info("completed cell data");
		} catch (IllegalAccessException iae) {
			logger.error("Error Accessing variable " + iae);
		}
	}

	public void overRideXYCo_ordinates(Object fieldObjects, int rowNum, int cellNum, HSSFSheet sheet) {
		if (sheet != null && sheet.getRow(rowNum) != null) {
			Row rowData = sheet.getRow(rowNum);
			Cell cellField = rowData.getCell(cellNum);
			if (cellField != null) {
				setCellValue(fieldObjects, cellField);
			}
		}
	}

	private void setCellValue(Object fieldObjects, Cell cellField) {
		if (fieldObjects instanceof Date)
			cellField.setCellValue((Date) fieldObjects);
		else if (fieldObjects instanceof Boolean)
			cellField.setCellValue((Boolean) fieldObjects);
		else if (fieldObjects instanceof String
				|| fieldObjects instanceof Integer | fieldObjects instanceof BigDecimal | fieldObjects instanceof Long)
			cellField.setCellValue(String.valueOf(fieldObjects));
		else if (fieldObjects instanceof Double)
			cellField.setCellValue((Double) fieldObjects);
	}

	private Field getMatchedFieldData(String fieldName, Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (field.getName() != null && fieldName != null && fieldName.equals(field.getName())) {
				return field;
			}
		}
		return null;
	}
}
