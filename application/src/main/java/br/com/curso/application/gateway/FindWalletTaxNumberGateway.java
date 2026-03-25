package br.com.curso.application.gateway;

import br.com.curso.core.domain.Wallet;

public interface FindWalletTaxNumberGateway {
    Wallet findByTaxNumber(String taxNumber);
}
