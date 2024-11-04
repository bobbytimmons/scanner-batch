package com.arbitrage.scannerbatch.common.exception;

public class BatchJobException extends Exception {
    public BatchJobException(String message) {
        super(message);
    }

    public BatchJobException(String message, Throwable cause) {
        super(message, cause);
    }
}
