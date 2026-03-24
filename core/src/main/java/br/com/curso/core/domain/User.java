package br.com.curso.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID id;
    private String nome;
    private String email;
    private String password;
    private TaxNumber taxNumber;
    private String fullName;
    private UserTypeEnum type;
    private TransactionPin transactionPin;
    private LocalDateTime creaedAt;
    private LocalDateTime updateAt;

    public User() {}

    public User(UUID id, String nome, String email, String password, TaxNumber taxNumber, String fullName, UserTypeEnum type, TransactionPin transactionPin, LocalDateTime creaedAt, LocalDateTime updateAt) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.taxNumber = taxNumber;
        this.fullName = fullName;
        this.type = type;
        this.transactionPin = transactionPin;
        this.creaedAt = creaedAt;
        this.updateAt = updateAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TaxNumber getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(TaxNumber taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public TransactionPin getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(TransactionPin transactionPin) {
        this.transactionPin = transactionPin;
    }

    public LocalDateTime getCreaedAt() {
        return creaedAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(nome, user.nome) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(taxNumber, user.taxNumber) && Objects.equals(fullName, user.fullName) && type == user.type && Objects.equals(transactionPin, user.transactionPin) && Objects.equals(creaedAt, user.creaedAt) && Objects.equals(updateAt, user.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, password, taxNumber, fullName, type, transactionPin, creaedAt, updateAt);
    }
}
