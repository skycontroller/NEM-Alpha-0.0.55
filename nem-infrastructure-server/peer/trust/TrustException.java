package org.nem.peer.trust;

public class TrustException
extends RuntimeException {
    public TrustException(String string) {
        super(string);
    }

    public TrustException(Throwable throwable) {
        super(throwable);
    }

    public TrustException(String string, Throwable throwable) {
        super(string, throwable);
    }
}