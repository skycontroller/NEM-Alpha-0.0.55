package org.nem.core.utils;

import java.nio.charset.Charset;

public class h {
    private static final Charset t = Charset.forName("UTF-8");

    public static byte[] getBytes(String string) {
        return string.getBytes(h.t);
    }

    public static String h(byte[] arrby) {
        return new String(arrby, h.t);
    }
}