package org.nem.core.crypto;

import java.util.Arrays;
import org.nem.core.crypto.CryptoException;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.EncodingException;
import org.nem.core.utils.g;

public class PublicKey
implements SerializableEntity {
    private static final int I = 33;
    public byte[] value;

    public PublicKey(byte[] arrby) {
        this.value = arrby;
    }

    public PublicKey(Deserializer deserializer) {
        this.value = deserializer.j("value");
    }

    public static PublicKey d(String string) {
        try {
            return new PublicKey(g.getBytes(string));
        }
        catch (EncodingException var1_1) {
            throw new CryptoException(var1_1);
        }
    }

    public byte[] o() {
        return this.value;
    }

    public boolean isCompressed() {
        if (33 != this.value.length) {
            return false;
        }
        switch (this.value[0]) {
            case 2: 
            case 3: {
                return true;
            }
        }
        return false;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("value", this.value);
    }

    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof PublicKey)) {
            return false;
        }
        PublicKey publicKey = (PublicKey)object;
        return Arrays.equals(this.value, publicKey.value);
    }

    public String toString() {
        return g.h(this.value);
    }
}