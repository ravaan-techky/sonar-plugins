package com.techky.sonar.report.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.techky.sonar.report.constants.ExportErrorCodes;
import com.techky.sonar.report.exception.ExportConfigurationException;

/**
 * The Class FileUtility.
 */
public class FileUtility {

	/**
	 * Write data.
	 *
	 * @param data         the data
	 * @param file         the file
	 * @param isAppendMode the is append mode
	 * @return true, if successful
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static boolean writeData(final String data, final File file, final boolean isAppendMode)
			throws ExportConfigurationException {
		final boolean writeResult = false;
		try (final FileWriter fileWriter = new FileWriter(file, isAppendMode);) {
			fileWriter.write(data);
			fileWriter.flush();
		} catch (final IOException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return writeResult;
	}

	/**
	 * Read data.
	 *
	 * @param file the file
	 * @return the string
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static String readData(final File file) throws ExportConfigurationException {
		final StringBuilder stringBuilder = new StringBuilder();
		try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
			while (true) {
				final String str = bufferedReader.readLine();
				if (str == null) {
					break;
				}
				stringBuilder.append(str);
			}
		} catch (final IOException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return stringBuilder.toString();
	}

	/**
	 * Instantiates a new file utility.
	 */
	private FileUtility() {
		// NO_PMD DUMMY Constructor.
	}
}
