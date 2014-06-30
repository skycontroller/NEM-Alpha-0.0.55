package org.nem.core.utils;

import org.apache.commons.codec.binary.Base32;
import org.nem.core.utils.h;

public class c {
    public static byte[] getBytes(String string) {
        Base32 base32 = new Base32();
        byte[] arrby = h.getBytes(string);
        if (base32.isInAlphabet(arrby, true)) return base32.decode(arrby);
        throw new IllegalArgumentException("malformed base32 string passed to getBytes");
    }

    public static String h(byte[] arrby) {
        Base32 base32 = new Base32();
        byte[] arrby2 = base32.encode(arrby);
        return h.h(arrby2);
    }
}