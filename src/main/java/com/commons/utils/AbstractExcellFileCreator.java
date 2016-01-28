package com.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface AbstractExcellFileCreator {
	int createExcellHeader(ExcelInfo excelInfo, HSSFSheet sheet, int rowNum);

	void createExcellRowsData(ExcelInfo excelInfo, HSSFSheet sheet, int rowNum);

	HSSFWorkbook createExcellWorkBook();
}
