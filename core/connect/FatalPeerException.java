package org.nem.core.connect;

public class FatalPeerException
extends RuntimeException {
    public FatalPeerException(String string) {
        super(string);
    }

    public FatalPeerException(Throwable throwable) {
        super(throwable);
    }

    public FatalPeerException(String string, Throwable throwable) {
        super(string, throwable);
    }
}