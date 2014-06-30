package org.nem.nis;

public class FatalConfigException
extends RuntimeException {
    public FatalConfigException(String string) {
        super(string);
    }

    public FatalConfigException(String string, Throwable throwable) {
        super(string, throwable);
    }
}