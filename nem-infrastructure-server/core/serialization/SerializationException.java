package org.nem.core.serialization;

public class SerializationException
extends RuntimeException {
    public SerializationException(String string) {
        super(string);
    }

    public SerializationException(Throwable throwable) {
        super(throwable);
    }

    public SerializationException(String string, Throwable throwable) {
        super(string, throwable);
    }
}