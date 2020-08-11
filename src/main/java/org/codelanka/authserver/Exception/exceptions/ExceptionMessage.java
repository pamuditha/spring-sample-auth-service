package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class ExceptionMessage.
 */
public class ExceptionMessage {

    /** The http status code. */
    private int statusCode;

    /** The custom error code. */
    private String errorCode;

    /** The error message. */
    private String errorMessage;

    /**
     * Instantiates a new exception message.
     *
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     */
    public ExceptionMessage(final int statusCode, final String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new exception message.
     *
     * @param statusCode
     *            the status code
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     */
    public ExceptionMessage(final int statusCode, final String errorCode, final String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the status code.
     *
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode
     *            the new error code
     */
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage
     *            the new error message
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}