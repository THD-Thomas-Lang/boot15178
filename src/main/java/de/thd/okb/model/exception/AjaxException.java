package de.thd.okb.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handling class for sending http stati.
 *
 * @author tlang
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AjaxException extends RuntimeException {
    /**
     * Overloaded constructor to build an exception message.
     *
     * @param message a given message
     */
    public AjaxException(String message) {
        super(message);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param throwable a given throwable
     */
    public AjaxException(Throwable throwable) {
        this(throwable.getMessage());
    }

}
