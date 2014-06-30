package org.nem.peer.node;

public class ImpersonatingPeerException
extends RuntimeException {
    public ImpersonatingPeerException(String string) {
        super(string);
    }

    public ImpersonatingPeerException(Throwable throwable) {
        super(throwable);
    }

    public ImpersonatingPeerException(String string, Throwable throwable) {
        super(string, throwable);
    }
}