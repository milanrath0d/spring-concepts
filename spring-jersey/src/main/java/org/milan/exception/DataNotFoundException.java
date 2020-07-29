package org.milan.exception;

/**
 * @author Milan Rathod
 */
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }

}
