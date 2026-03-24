package br.com.curso.usecase;

import br.com.curso.core.domain.User;

public interface FindUserTaxNumberUseCase {
    User findByTaxNumber(String taxNumber);
}
