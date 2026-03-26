package br.com.curso.core.exception.enums;

public enum ErrorCodeEnum {

    ON0001("TaxNumber invalid.", "ON-0001"),
    ON0002("TaxNumber Unavailable.", "ON-0002"),
    ON0003("Email Unavailable.", "ON-0003"),
    ON0004("Error creating user.", "ON-0004"),

    TR0001("The Shopekeeper user does not have the transfer function available.", "TR-0001"),
    TR0002("Unavailable balance.", "TR-0002"),
    TR0003("There was an error while performing the transfer.", "TR-0003"),
    TR0004("unauthorized transfer.", "TR-0003"),

    TRP0001("Invalid Pin.", "TRP-0001"),

    WA0001("Wallet not found.", "WA-0001"),

    NO0001("There was an notification user.", "NO-0001"),

    ATH001("There was an authentication error.", "ATH-001"),

    PIN001("TransactionPin Blocked", "PIN-001"),
    PIN002("Incorrect Pin. %d Remaining attempt(s).", "PIN-002"),

    ;

    private String message;
    private String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String pin0002Getmessage(Integer attempt) {
        return String.format(PIN002.message, attempt);
    }
}
