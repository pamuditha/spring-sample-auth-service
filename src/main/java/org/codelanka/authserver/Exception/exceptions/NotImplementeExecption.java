package org.codelanka.authserver.Exception.exceptions;

/**
 * The Class NotImplementeExecption.
 */
public class NotImplementeExecption extends AuthServiceException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6894342693901033917L;

    /**
     * Instantiates a new not implemented exception.
     *
     * @param status
     *            the status code
     * @param msg
     *            the message
     */
    public NotImplementeExecption(final int status, final String msg) {
        super(status, msg);
    }

    /**
     * Instantiates a new not implemented exception.
     *
     * @param status
     *            the status
     * @param code
     *            the code
     * @param msg
     *            the message
     */
    public NotImplementeExecption(final int status, final String code, final String msg) {
        super(status, code, msg);
    }
}