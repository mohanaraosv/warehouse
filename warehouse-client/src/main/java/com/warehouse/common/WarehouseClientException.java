
package com.warehouse.common;

/**
 * general exception class for Warehouse client Application.
 * 
 */
public class WarehouseClientException extends Exception {

    private static final long serialVersionUID = -24932986778500014L;

    private int errorCode;

    /**
     * Constructor.
     */
    public WarehouseClientException() {
        super();

    }

    /**
     * constructor.
     * 
     * @param errorMessage String
     * @param errorCause Throwable
     */
    public WarehouseClientException(final String errorMessage, final Throwable errorCause) {
        super(errorMessage, errorCause);

    }

    /**
     * constructor.
     * 
     * @param errorMessage
     * @param errorCode
     * @param errorCause
     */
    public WarehouseClientException(final String errorMessage, final int errorCode, final Throwable errorCause) {
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
    public WarehouseClientException(final int errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * Constructor.
     * 
     * @param errorMessage string
     */
    public WarehouseClientException(final String errorMessage) {
        super(errorMessage);

    }

    /**
     * Constructor.
     * 
     * @param errorCause Throwable
     */
    public WarehouseClientException(final Throwable errorCause) {
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
