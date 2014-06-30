package org.nem.core.crypto;

import java.math.BigInteger;
import org.nem.core.crypto.CryptoException;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.EncodingException;
import org.nem.core.utils.g;

public class PrivateKey
implements SerializableEntity {
    private final BigInteger H;

    public PrivateKey(BigInteger bigInteger) {
        this.H = bigInteger;
    }

    public PrivateKey(Deserializer deserializer) {
        this.H = deserializer.i("value");
    }

    public BigInteger v() {
        return this.H;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("value", this.H);
    }

    public int hashCode() {
        return this.H.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof PrivateKey)) {
            return false;
        }
        PrivateKey privateKey = (PrivateKey)object;
        return this.H.equals(privateKey.H);
    }

    public String toString() {
        return g.h(this.H.toByteArray());
    }

    public static PrivateKey b(String string) {
        try {
            return new PrivateKey(new BigInteger(g.getBytes(string)));
        }
        catch (EncodingException var1_1) {
            throw new CryptoException(var1_1);
        }
    }

    public static PrivateKey c(String string) {
        try {
            return new PrivateKey(new BigInteger(string, 10));
        }
        catch (NumberFormatException var1_1) {
            throw new CryptoException(var1_1);
        }
    }

    private static PrivateKey a(String string, int n) {
        try {
            return new PrivateKey(new BigInteger(string, n));
        }
        catch (NumberFormatException var2_2) {
            throw new CryptoException(var2_2);
        }
    }
}