
package org.codelanka.authserver.Exception.exceptions;


public class AuthServiceException extends RuntimeException {

    private static final long serialVersionUID = -8355773133441167738L;

    private int status;

    private String code;

    public AuthServiceException(final int status, final String msg) {
        super(msg);
        this.status = status;
    }

    public AuthServiceException(final int status, final String code, final String msg) {
        super(msg);
        this.status = status;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }
}