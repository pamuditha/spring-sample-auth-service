package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class InvalidRequestException.
 */
public class InvalidRequestException extends AuthServiceException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -364584260974486019L;

    /**
     * Instantiates a new invalid request exception.
     *
     * @param status
     *            the status code
     * @param msg
     *            the message
     */
    public InvalidRequestException(final int status, final String msg) {
        super(status, msg);
    }

    /**
     * Instantiates a new invalid request exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param msg
     *            the message
     */
    public InvalidRequestException(final int status, final String code, final String msg) {
        super(status, code, msg);
    }
}