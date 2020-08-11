package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class InternalServerException.
 */
public class InternalServerException extends AuthServiceException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4190944639277923321L;

    /**
     * Instantiates a new internal server exception.
     *
     * @param status
     *            the status code
     * @param msg
     *            the message
     */
    public InternalServerException(final int status, final String msg) {
        super(status, msg);
    }

    /**
     * Instantiates a new internal server exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param msg
     *            the message
     */
    public InternalServerException(final int status, final String code, final String msg) {
        super(status, code, msg);
    }
}