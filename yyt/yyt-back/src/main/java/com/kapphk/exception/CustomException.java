package com.kapphk.exception;
/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月23日 上午10:46:13 
*
*/
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public CustomException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
