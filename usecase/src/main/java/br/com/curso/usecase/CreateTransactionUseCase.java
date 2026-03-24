package br.com.curso.usecase;

import br.com.curso.core.domain.Transaction;

public interface CreateTransactionUseCase {
    void create(Transaction transaction);
}

