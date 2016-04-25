package com.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
/**
 * This class is used to Excute and get current enviroment varibales results
 * @author vrengasamy
 *
 */
public class SystemUtils {

	private static final Logger logger = Logger.getLogger(SystemUtils.class);
	private static final String os_name = System.getProperty("os.name");
	private static final String Linux = "Linux";
	private static final String Windows = "Windows";

	public static void main(String args[]) {
		// DISABLE_BATCH_JOB
		System.out.println(System.getenv("DISABLE_BATCH_JOB"));
		System.out.println(getDynaEnvVariables("DISABLE_BATCH_JOB").trim());
	}

	public static String getDynaEnvVariables(String varName) {
		if (os_name.contains(Windows)) {
			return excuteCommand(prepareCommand("echo " + varName));
		} else {
			return excuteCommand(prepareCommand("echo " + "$" + varName));
		}
	}

	private static String[] prepareCommand(String taskCommand) {
		if (os_name.contains(Windows)) {
			return getWindows(taskCommand);
		} else if (os_name.contains(Linux)) {
			return getLinuxOrUnix(taskCommand);
		} else {
			return getLinuxOrUnix(taskCommand);
		}
	}

	private static final String[] getWindows(String taskCommand) {
		String[] commandToExecute = new String[3];
		commandToExecute[0] = "cmd.exe";
		commandToExecute[1] = "/c";
		commandToExecute[2] = taskCommand;
		return commandToExecute;

	}

	private static final String[] getLinuxOrUnix(String taskCommand) {
		String[] commandToExecute = new String[3];
		commandToExecute[0] = "/bin/sh";
		commandToExecute[1] = "-c";
		commandToExecute[2] = taskCommand;
		return commandToExecute;

	}

	private static String excuteCommand(String[] command) {
		Runtime rt = Runtime.getRuntime();
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		Process process = null;
		try {
			process = rt.exec(command);
			if (process != null) {
				process.waitFor();
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					builder.append(line + "\n");
				}
			}
		} catch (IOException | InterruptedException e) {
			logger.error(e.getMessage());
		} finally {
			if (process != null) {
				process.destroy();
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		return builder.toString();
	}

}
