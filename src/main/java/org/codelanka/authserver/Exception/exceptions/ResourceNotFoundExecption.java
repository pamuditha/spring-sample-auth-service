package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class ResourceNotFoundExecption.
 */
public class ResourceNotFoundExecption extends AuthServiceException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6894342693901013317L;

    /**
     * Instantiates a new resource not found exception.
     *
     * @param status
     *            the status code
     * @param msg
     *            the message
     */
    public ResourceNotFoundExecption(final int status, final String msg) {
        super(status, msg);
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param msg
     *            the message
     */
    public ResourceNotFoundExecption(final int status, final String code, final String msg) {
        super(status, code, msg);
    }
}