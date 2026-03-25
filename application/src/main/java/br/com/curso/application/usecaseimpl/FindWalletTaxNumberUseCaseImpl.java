package br.com.curso.application.usecaseimpl;

import br.com.curso.application.gateway.FindWalletTaxNumberGateway;
import br.com.curso.core.domain.Wallet;
import br.com.curso.core.exception.NotFoundException;
import br.com.curso.core.exception.enums.ErrorCodeEnum;
import br.com.curso.usecase.FindWalletTaxNumberUseCase;

public class FindWalletTaxNumberUseCaseImpl implements FindWalletTaxNumberUseCase {

    private FindWalletTaxNumberGateway findWalletTaxNumberGateway;

    public FindWalletTaxNumberUseCaseImpl(FindWalletTaxNumberGateway findWalletTaxNumberGateway) {
        this.findWalletTaxNumberGateway = findWalletTaxNumberGateway;
    }

    @Override
    public Wallet findByTaxNumber(String taxNumber) throws NotFoundException {
        Wallet wallet = findWalletTaxNumberGateway.findByTaxNumber(taxNumber);

        if(wallet == null){
            throw new NotFoundException(ErrorCodeEnum.WA0001.getMessage(), ErrorCodeEnum.WA0001.getCode());
        }

        return wallet;
    }
}
