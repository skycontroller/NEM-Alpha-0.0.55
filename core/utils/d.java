package org.nem.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.nem.core.utils.h;

public class d {
    public static byte[] getBytes(String string) {
        Base64 base64 = new Base64();
        byte[] arrby = h.getBytes(string);
        if (base64.isInAlphabet(arrby, true)) return base64.decode(arrby);
        throw new IllegalArgumentException("malformed base64 string passed to getBytes");
    }

    public static String h(byte[] arrby) {
        Base64 base64 = new Base64();
        byte[] arrby2 = base64.encode(arrby);
        return h.h(arrby2);
    }
}