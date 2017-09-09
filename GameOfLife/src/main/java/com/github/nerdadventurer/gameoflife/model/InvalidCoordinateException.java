/**
 * 
 */
package com.github.nerdadventurer.gameoflife.model;

/**
 * Exception class thrown for invalid coordinates.
 * @author Aki Laukkanen
 */
public class InvalidCoordinateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	public InvalidCoordinateException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvalidCoordinateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvalidCoordinateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidCoordinateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidCoordinateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
