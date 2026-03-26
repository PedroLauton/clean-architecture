package br.com.curso.application.usecaseimpl;

import br.com.curso.application.gateway.TransactionPinValidateGateway;
import br.com.curso.core.domain.TransactionPin;
import br.com.curso.core.exception.PinException;
import br.com.curso.core.exception.TransferException;
import br.com.curso.core.exception.enums.ErrorCodeEnum;
import br.com.curso.usecase.TransactionPinValidateUseCase;
import br.com.curso.usecase.UpdateTransactionPinUseCase;

public class TransactionPinValidateUseCaseImpl implements TransactionPinValidateUseCase {

    private TransactionPinValidateGateway transactionPinValidateGateway;
    private UpdateTransactionPinUseCase updateTransactionPinUseCase;

    public TransactionPinValidateUseCaseImpl(TransactionPinValidateGateway transactionPinValidateGateway, UpdateTransactionPinUseCase updateTransactionPinUseCase) {
        this.transactionPinValidateGateway = transactionPinValidateGateway;
        this.updateTransactionPinUseCase = updateTransactionPinUseCase;
    }

    @Override
    public Boolean validate(TransactionPin transactionPin) throws TransferException, PinException {
        if(transactionPin.getBlocked()) throw new PinException(ErrorCodeEnum.PIN001.getMessage(), ErrorCodeEnum.PIN001.getCode());

        if (!transactionPinValidateGateway.validate(transactionPin)){
            transactionPin.setAttempt();
            var transactionPinUpdated = updateTransactionPinUseCase.update(transactionPin);
            throw new PinException(ErrorCodeEnum.pin0002Getmessage(transactionPinUpdated.getAttempt()), ErrorCodeEnum.PIN002.getCode());
        }

        if (transactionPin.getAttempt() < 3) {
            transactionPin.restaureAttempt();
            updateTransactionPinUseCase.update(transactionPin);
        }

        return true;
    }
}
