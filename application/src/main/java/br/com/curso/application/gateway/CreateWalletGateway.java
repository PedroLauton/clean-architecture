package br.com.curso.application.gateway;

import br.com.curso.core.domain.Wallet;

public interface CreateWalletGateway {

    void create(Wallet wallet);
}
