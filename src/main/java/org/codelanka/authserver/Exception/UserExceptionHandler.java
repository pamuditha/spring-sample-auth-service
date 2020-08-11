package org.codelanka.authserver.Exception;

import org.codelanka.authserver.Exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Resource Not Found exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(ResourceNotFoundExecption.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionMessage resourceNotFoundExecption(final HttpServletRequest req,
                                                      final ResourceNotFoundExecption ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }

    /**
     * Content Not Found exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(ContentNotFoundExecption.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public ExceptionMessage contentNotFoundExecption(final HttpServletRequest req, final ContentNotFoundExecption ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }

    /**
     * Invalid request exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage invalidRequestException(final HttpServletRequest req, final InvalidRequestException ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }

    /**
     * Internal server exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionMessage internalServerException(final HttpServletRequest req, final InternalServerException ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }



    /**
     * Not implemented exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(NotImplementeExecption.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public ExceptionMessage notImplementeExecption(final HttpServletRequest req, final NotImplementeExecption ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }

    /**
     * Forbidden exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionMessage forbiddenExecption(final HttpServletRequest req, final ForbiddenException ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }

    /**
     * Unauthorized exception.
     *
     * @param req
     *            the servlet request entity
     * @param ex
     *            the exception
     * @return the exception message
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionMessage unauthorizedException(final HttpServletRequest req, final UnauthorizedException ex) {

        return new ExceptionMessage(ex.getStatus(), ex.getCode(), ex.getMessage());
    }
}
