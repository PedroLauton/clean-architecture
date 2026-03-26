package br.com.curso.application.usecaseimpl;

import br.com.curso.application.gateway.TransferGateway;
import br.com.curso.core.domain.Transaction;
import br.com.curso.core.domain.Wallet;
import br.com.curso.core.exception.*;
import br.com.curso.core.exception.enums.ErrorCodeEnum;
import br.com.curso.usecase.*;

import java.math.BigDecimal;

public class TransferUseCaseImpl implements TransferUseCase {

    private FindWalletTaxNumberUseCase findWalletTaxNumberUseCase;
    private TransactionValidateUseCase transactionValidateUseCase;
    private CreateTransactionUseCase createTransactionUseCase;
    private TransferGateway transferGateway;
    private UserNotificationUseCase userNotificationUseCase;
    private TransactionPinValidateUseCaseImpl transactionPinValidateUseCaseImpl;

    public TransferUseCaseImpl(FindWalletTaxNumberUseCase findWalletTaxNumberUseCase, TransactionValidateUseCase transactionValidateUseCase,
                               CreateTransactionUseCase createTransactionUseCase, TransferGateway transferGateway,
                               UserNotificationUseCase userNotificationUseCase, TransactionPinValidateUseCaseImpl transactionPinValidateUseCaseImpl) {
        this.findWalletTaxNumberUseCase = findWalletTaxNumberUseCase;
        this.transactionValidateUseCase = transactionValidateUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.transferGateway = transferGateway;
        this.userNotificationUseCase = userNotificationUseCase;
        this.transactionPinValidateUseCaseImpl = transactionPinValidateUseCaseImpl;
    }

    @Override
    public Boolean transfer(String toTaxNumber, String fromTaxNumber, BigDecimal value, String pin) throws InternalServerErrorException, TransferException, NotFoundException, NotificationException, PinException {
        Wallet from = findWalletTaxNumberUseCase.findByTaxNumber(fromTaxNumber);
        Wallet to = findWalletTaxNumberUseCase.findByTaxNumber(toTaxNumber);

        if(from.getTransactionPin().getBlocked()) throw new PinException(ErrorCodeEnum.PIN001.getMessage(), ErrorCodeEnum.PIN001.getCode());

        transactionPinValidateUseCaseImpl.validate(from.getTransactionPin());

        from.transferValue(value);
        to.receiveValue(value);

        Transaction transaction = createTransactionUseCase.create(new Transaction(from, to, value));

        transactionValidateUseCase.validate(transaction);

        if(!transferGateway.transfer(transaction)){
            throw new InternalServerErrorException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }

        if(!userNotificationUseCase.notificate(transaction, to.getUser().getEmail())) {
            throw new NotificationException(ErrorCodeEnum.TR0001.getMessage(), ErrorCodeEnum.TR0002.getCode());
        }

        return true;
    }
}
