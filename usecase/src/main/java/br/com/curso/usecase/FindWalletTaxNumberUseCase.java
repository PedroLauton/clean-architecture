package br.com.curso.usecase;

import br.com.curso.core.domain.User;
import br.com.curso.core.domain.Wallet;
import br.com.curso.core.exception.NotFoundException;

public interface FindWalletTaxNumberUseCase {
    Wallet findByTaxNumber(String taxNumber) throws NotFoundException;
}
