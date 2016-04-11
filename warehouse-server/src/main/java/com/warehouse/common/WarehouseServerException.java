
package com.warehouse.common;

/**
 * general exception class for WarehouseRecevingException Application.
 * 
 */
public class WarehouseServerException extends Exception {

    private static final long serialVersionUID = 4963365543221056611L;

    private int errorCode;

    /**
     * Constructor.
     */
    public WarehouseServerException() {
        super();

    }

    /**
     * constructor.
     * 
     * @param errorMessage String
     * @param errorCause Throwable
     */
    public WarehouseServerException(final String errorMessage, final Throwable errorCause) {
        super(errorMessage, errorCause);

    }

    /**
     * constructor.
     * 
     * @param errorMessage
     * @param errorCode
     * @param errorCause
     */
    public WarehouseServerException(final String errorMessage, final int errorCode, final Throwable errorCause) {
        super(errorMessage, errorCause);
        this.errorCode = errorCode;
    }

    /**
     * constructor.
     * 
     * @param errorMessage
     * @param errorCode
     * @param errorCause
     */
    public WarehouseServerException(final int errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * Constructor.
     * 
     * @param errorMessage String
     */
    public WarehouseServerException(final String errorMessage) {
        super(errorMessage);

    }

    /**
     * Constructor.
     * 
     * @param errorCause Throwable
     */
    public WarehouseServerException(final Throwable errorCause) {
        super(errorCause);

    }

    /**
     * getter for error code.
     * 
     * @return int
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * setter for error code.
     * 
     * @param errorCode int
     */
    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }
}
