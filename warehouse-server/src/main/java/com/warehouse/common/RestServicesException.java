package com.warehouse.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pavankumar.s
 * 
 */
@XmlRootElement
public class RestServicesException implements Serializable {

	private static final long serialVersionUID = -1026633162435717406L;

	private int errorCode;
	private String errorMessage;
	public RestServicesException() {
		super();
	}

	public RestServicesException(int errorCode,String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
