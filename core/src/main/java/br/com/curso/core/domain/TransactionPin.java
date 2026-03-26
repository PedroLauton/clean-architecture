package br.com.curso.core.domain;

import br.com.curso.core.exception.TransactionPinException;
import br.com.curso.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {

    private Long id;
    private String pin;
    private Integer attempt;
    private Boolean blocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TransactionPin() {
    }

    public TransactionPin(Long id, String pin, Integer attempt, Boolean blocked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TransactionPin(String pin) throws TransactionPinException {
        setPin(pin);
        this.attempt = 3;
        this.blocked = false;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) throws TransactionPinException {
        pinIsValid(pin);
        this.pin = pin;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt() {
        if(this.attempt == 1) {
            this.blocked = true;
            this.attempt = 0;
        } else {
            this.attempt = this.attempt - 1;
        }
    }

    public void restaureAttempt() {
        this.attempt = 3;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TransactionPin that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(pin, that.pin) && Objects.equals(attempt, that.attempt) && Objects.equals(blocked, that.blocked) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pin, attempt, blocked, createdAt, updatedAt);
    }

    private void pinIsValid(String pin) throws TransactionPinException {
        if(pin.length() != 8) {
            throw new TransactionPinException(ErrorCodeEnum.TRP0001.getMessage(), ErrorCodeEnum.TRP0001.getCode());
        }
    }
}
