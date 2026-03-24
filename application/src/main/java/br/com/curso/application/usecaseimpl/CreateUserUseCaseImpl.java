package br.com.curso.application.usecaseimpl;

import br.com.curso.application.gateway.CreateUserGateway;
import br.com.curso.core.domain.TransactionPin;
import br.com.curso.core.domain.User;
import br.com.curso.core.domain.Wallet;
import br.com.curso.core.exception.EmailException;
import br.com.curso.core.exception.TaxNumberException;
import br.com.curso.core.exception.TransactionPinException;
import br.com.curso.core.exception.enums.ErrorCodeEnum;
import br.com.curso.usecase.*;

import java.math.BigDecimal;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private TaxNumberAvailableUseCase taxNumberAvailableUseCase;
    private EmailAvailableUseCase emailAvailableUseCase;
    private CreateUserGateway createUserGateway;
    private CreateWalletUseCase createWalletUseCase;
    private CreateTransactionPinUseCase createTransactionPinUseCase;

    public CreateUserUseCaseImpl(TaxNumberAvailableUseCase taxNumberAvailableUseCase, EmailAvailableUseCase emailAvailableUseCase,
                                 CreateUserGateway createUserGateway, CreateWalletUseCase createWalletUseCase,
                                 CreateTransactionPinUseCase createTransactionPinUseCase) {
        this.taxNumberAvailableUseCase = taxNumberAvailableUseCase;
        this.emailAvailableUseCase = emailAvailableUseCase;
        this.createUserGateway = createUserGateway;
        this.createWalletUseCase = createWalletUseCase;
        this.createTransactionPinUseCase = createTransactionPinUseCase;
    }

    @Override
    public void create(User user, String pin) throws TaxNumberException, EmailException, TransactionPinException {
        if(!taxNumberAvailableUseCase.TaxNumberAvailable(user.getTaxNumber().getValue())) {
            throw new TaxNumberException(ErrorCodeEnum.ON0002.getMessage(), ErrorCodeEnum.ON0002.getCode());
        }

        if(!emailAvailableUseCase.emailAvailableEmail(user.getEmail())) {
            throw new EmailException(ErrorCodeEnum.ON0003.getMessage(), ErrorCodeEnum.ON0003.getCode());
        }

        var userSaved =  createUserGateway.create(user);
        createWalletUseCase.create(new Wallet(BigDecimal.ZERO, userSaved));
        createTransactionPinUseCase.create(new TransactionPin(userSaved, pin));
    }
}
