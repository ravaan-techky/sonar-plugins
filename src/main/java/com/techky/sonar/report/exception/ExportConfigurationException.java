package com.techky.sonar.report.exception;

import com.techky.sonar.report.constants.ExportErrorCodes;

/**
 * Class used for export configuration exception.
 *
 * @author Lavanya
 */
public class ExportConfigurationException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** errorCode Integer. */
	private final Integer errorCode;

	/** errorMessage string */
	private final String errorMessage;

	/**
	 * Parameterized constructor.
	 *
	 * @param exportErrorCodes ExportErrorCodes.
	 * @param errorMessage     string.
	 */
	public ExportConfigurationException(final ExportErrorCodes exportErrorCodes, final String errorMessage) {
		super(errorMessage);
		this.errorCode = exportErrorCodes.getErrorCode();
		this.errorMessage = errorMessage;
	}

	/**
	 * Parameterized constructor.
	 *
	 * @param exportErrorCodes ExportErrorCodes.
	 * @param exception        Exception.
	 */
	public ExportConfigurationException(final ExportErrorCodes exportErrorCodes, final Exception exception) {
		super(exception);
		this.errorCode = exportErrorCodes.getErrorCode();
		this.errorMessage = exception.getLocalizedMessage();
	}

	/**
	 * Getter method for errorCode.
	 *
	 * @return errorCode Integer.
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * Getter method for errorMessage.
	 *
	 * @return errorMessage string.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
