package br.com.danielcesario.myprojecmanager.core.utilities.exception;

import lombok.Generated;

@Generated
public class GatewayException extends RuntimeException {
    public GatewayException(final String erroMessage) {
        super(erroMessage);
    }

}
