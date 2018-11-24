package com.ryanafzal.io.calculator.resources.misc;

/**
 * A special kind of exception, which contains an error code. This allows for special handling of exceptions, depending on the cause.
 * @author s-afzalr
 *
 */
public abstract class ErrorCodeException extends Exception {
	
	private static final long serialVersionUID = 8364091948197579878L;
	
	/**
	 * The error code of this exception.
	 */
	private final ErrorCode ERRORCODE;
	
	/**
	 * Error codes to describe errors.
	 * @author s-afzalr
	 */
	public enum ErrorCode {
		
	}
	
	public ErrorCodeException(ErrorCode errorCode) {
		super();
		this.ERRORCODE = errorCode;
	}

	public ErrorCodeException(String arg0, ErrorCode errorCode) {
		super(arg0);
		this.ERRORCODE = errorCode;
	}

	public ErrorCodeException(Throwable arg0, ErrorCode errorCode) {
		super(arg0);
		this.ERRORCODE = errorCode;
	}

	public ErrorCodeException(String arg0, Throwable arg1, ErrorCode errorCode) {
		super(arg0, arg1);
		this.ERRORCODE = errorCode;
	}

	public ErrorCodeException(String arg0, Throwable arg1, boolean arg2, boolean arg3, ErrorCode errorCode) {
		super(arg0, arg1, arg2, arg3);
		this.ERRORCODE = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return this.ERRORCODE;
	}

}
