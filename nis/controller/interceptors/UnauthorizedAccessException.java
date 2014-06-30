package org.nem.nis.controller.interceptors;

public class UnauthorizedAccessException
extends RuntimeException {
    public UnauthorizedAccessException(String string) {
        super(string);
    }
}