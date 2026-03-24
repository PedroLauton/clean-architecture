package br.com.curso.usecase;

import br.com.curso.core.domain.Transaction;

public interface TransferUseCase {
    Boolean transfer(Transaction transaction);
}
