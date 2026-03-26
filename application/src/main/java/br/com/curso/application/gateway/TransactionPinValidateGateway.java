package br.com.curso.application.gateway;

import br.com.curso.core.domain.TransactionPin;
import br.com.curso.core.exception.InternalServerErrorException;

public interface TransactionPinValidateGateway {

    Boolean validate(TransactionPin pin);
}
