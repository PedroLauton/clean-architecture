package br.com.curso.application.usecaseimpl;

import br.com.curso.application.gateway.CreateWalletGateway;
import br.com.curso.core.domain.Wallet;
import br.com.curso.usecase.CreateWalletUseCase;

public class CreateWalletUseCaseImpl implements CreateWalletUseCase {

    private CreateWalletGateway createWalletGateway;

    public CreateWalletUseCaseImpl(CreateWalletGateway createWalletGateway) {
        this.createWalletGateway = createWalletGateway;
    }

    @Override
    public void create(Wallet wallet) {
        createWalletGateway.create(wallet);
    }
}
