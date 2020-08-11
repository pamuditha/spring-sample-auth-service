package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class ForbiddenException.
 */
public class ForbiddenException extends AuthServiceException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6026551389652689297L;

    /**
     * Instantiates a new forbidden exception.
     *
     * @param status
     *            the status code
     * @param msg
     *            the message
     */
    public ForbiddenException(final int status, final String msg) {
        super(status, msg);
    }

    /**
     * Instantiates a new forbidden exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param msg
     *            the message
     */
    public ForbiddenException(final int status, final String code, final String msg) {
        super(status, code, msg);
    }
}