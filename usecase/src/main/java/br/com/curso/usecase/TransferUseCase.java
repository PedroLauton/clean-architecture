package br.com.curso.usecase;

import br.com.curso.core.domain.Transaction;
import br.com.curso.core.exception.*;

import java.math.BigDecimal;

public interface TransferUseCase {
    Boolean transfer(String toTaxNumber, String fromTaxNumber, BigDecimal value, String pin) throws InternalServerErrorException, TransferException, NotFoundException, NotificationException, PinException;
}
