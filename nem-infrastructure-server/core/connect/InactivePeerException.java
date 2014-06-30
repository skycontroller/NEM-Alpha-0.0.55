package org.nem.core.connect;

public class InactivePeerException
extends RuntimeException {
    public InactivePeerException(String string) {
        super(string);
    }

    public InactivePeerException(Throwable throwable) {
        super(throwable);
    }

    public InactivePeerException(String string, Throwable throwable) {
        super(string, throwable);
    }
}