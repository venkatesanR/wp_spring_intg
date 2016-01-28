package com.commons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileUtils {
	private static final transient Logger logger = Logger.getLogger(FileUtils.class);

	@SuppressWarnings("unchecked")
	/**
	 * This method is used to convert file data info to List of String if we give
	 * isListOfLines=true else it will prepare Single line of information
	 * @param fileName
	 * @param isListOfLines
	 * @return
	 */
	public static <T> T fileToString(String fileName, boolean isListOfLines) {
		File csvFile = new File(fileName);
		if (!csvFile.exists())
			logger.info("no such a file in a directory");
		T outPut = null;
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(csvFile));
			String fileData;
			if (isListOfLines) {
				List<String> linesList = new ArrayList<String>();
				while ((fileData = bReader.readLine()) != null) {
					linesList.add(fileData);
				}
				outPut = (T) linesList;
			} else {
				StringBuilder builder = new StringBuilder();
				while ((fileData = bReader.readLine()) != null) {
					builder.append(fileData);
				}
				outPut = (T) builder.toString();
			}
		} catch (FileNotFoundException fnf) {
			logger.error("no such a file in a directory");
		} catch (IOException io) {
			logger.error("Critical IO Error");
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				logger.error("Critical IO Error");
			}
		}
		return outPut;
	}
}
