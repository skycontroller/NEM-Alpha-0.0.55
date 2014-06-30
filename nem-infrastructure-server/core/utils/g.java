package org.nem.core.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.nem.core.utils.EncodingException;
import org.nem.core.utils.h;

public class g {
    public static byte[] getBytes(String string) {
        Hex hex = new Hex();
        String string2 = 0 == string.length() % 2 ? string : "0" + string;
        byte[] arrby = h.getBytes(string2);
        try {
            return hex.decode(arrby);
        }
        catch (DecoderException var4_4) {
            throw new EncodingException((Throwable)var4_4);
        }
    }

    public static String h(byte[] arrby) {
        Hex hex = new Hex();
        byte[] arrby2 = hex.encode(arrby);
        return h.h(arrby2);
    }
}