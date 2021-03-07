package br.com.danielcesario.myprojecmanager.core.utilities.exception;

import lombok.Generated;

@Generated
public class BusinessException extends RuntimeException {
    public BusinessException(final String errorMessage) {
        super(errorMessage);
    }

}
