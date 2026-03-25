package br.com.curso.usecase;

import br.com.curso.core.domain.Transaction;
import br.com.curso.core.exception.InternalServerErrorException;
import br.com.curso.core.exception.NotFoundException;
import br.com.curso.core.exception.NotificationException;
import br.com.curso.core.exception.TransferException;

import java.math.BigDecimal;

public interface TransferUseCase {
    Boolean transfer(String toTaxNumber, String fromTaxNumber, BigDecimal value) throws InternalServerErrorException, TransferException, NotFoundException, NotificationException;
}
