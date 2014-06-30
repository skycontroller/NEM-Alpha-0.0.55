package org.nem.core.model;

import java.util.Arrays;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.c;
import org.nem.core.model.m;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.b;
import org.nem.core.utils.c;

public class Address {
    private static final int aP = 4;
    private static final int aQ = 25;
    private String aR;
    private PublicKey G;

    public static Address a(PublicKey publicKey) {
        return new Address(m.aR().aL(), publicKey);
    }

    public static Address e(String string) {
        return new Address(string.toUpperCase());
    }

    private Address(String string) {
        this.aR = string;
        this.G = null;
    }

    private Address(byte by, PublicKey publicKey) {
        this.aR = Address.a(by, publicKey.o());
        this.G = publicKey;
    }

    private static String a(byte by, byte[] arrby) {
        byte[] arrby2 = c.a(arrby);
        byte[] arrby3 = c.b(arrby2);
        byte[] arrby4 = b.c({by}, arrby3);
        byte[] arrby5 = Address.d(arrby4);
        byte[] arrby6 = b.c(arrby4, arrby5);
        return org.nem.core.utils.c.h(arrby6);
    }

    private static byte[] d(byte[] arrby) {
        byte[] arrby2 = c.a(arrby);
        return Arrays.copyOfRange(arrby2, 0, 4);
    }

    public String ax() {
        return this.aR;
    }

    public PublicKey getPublicKey() {
        return this.G;
    }

    public boolean isValid() {
        byte[] arrby;
        try {
            arrby = org.nem.core.utils.c.getBytes(this.aR);
        }
        catch (IllegalArgumentException var2_2) {
            return false;
        }
        if (25 != arrby.length) {
            return false;
        }
        if (m.aR().aL() != arrby[0]) {
            return false;
        }
        int n = 21;
        byte[] arrby2 = Arrays.copyOfRange(arrby, 0, n);
        byte[] arrby3 = Arrays.copyOfRange(arrby, n, n + 4);
        byte[] arrby4 = Address.d(arrby2);
        return Arrays.equals(arrby3, arrby4);
    }

    public int hashCode() {
        return this.aR.toLowerCase().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof Address)) {
            return false;
        }
        Address address = (Address)object;
        return this.aR.equals(address.aR);
    }

    public String toString() {
        return this.aR;
    }

    public static void a(Serializer serializer, String string, Address address) {
        serializer.a(string, address.ax());
    }

    public static Address c(Deserializer deserializer, String string) {
        String string2 = deserializer.k(string);
        return Address.e(string2);
    }
}