package pl.semantyk.exceptions;

/**
 * Typ wyliczeniowy zawierający kody błędów warstwy dostępu do danych.
 */
public enum DataAccessCode implements ErrorCode {

    NO_ENTITY_FOUND(101),
    NO_SINGLE_RESULT(102),
    ILLEGAL_ARGUMENT(103),
    TRANSACTION_REQUIRED(104),
    SQL_EXCEPTION(105);

    private DataAccessCode(int code) {
        this.code = code;
    }

    private final int code;

    @Override
    public int getErrorCode() {
        return code;
    }

    public static DataAccessCode enumOf(int code) {
        for (DataAccessCode b : DataAccessCode.values()) {
            if (code == b.getErrorCode()) {
                return b;
            }
        }
        return null;
    }
}
