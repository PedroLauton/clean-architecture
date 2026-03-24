package br.com.curso.usecase;

import br.com.curso.core.domain.TransactionPin;
import br.com.curso.core.domain.Wallet;

public interface CreateWalletUseCase {
    void create(Wallet wallet);
}
