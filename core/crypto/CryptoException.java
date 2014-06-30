package org.nem.core.crypto;

public class CryptoException
extends RuntimeException {
    public CryptoException(String string) {
        super(string);
    }

    public CryptoException(Throwable throwable) {
        super(throwable);
    }

    public CryptoException(String string, Throwable throwable) {
        super(string, throwable);
    }
}