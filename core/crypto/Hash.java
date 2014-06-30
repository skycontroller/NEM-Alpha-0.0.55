package org.nem.core.crypto;

import java.util.Arrays;
import org.nem.core.crypto.CryptoException;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.EncodingException;
import org.nem.core.utils.e;
import org.nem.core.utils.g;

public class Hash
implements SerializableEntity {
    public static final Hash C = new Hash(new byte[32]);
    public static final ObjectDeserializer<Hash> D = new ObjectDeserializer<Hash>(){

        @Override
        public Hash deserialize(Deserializer deserializer) {
            return new Hash(deserializer);
        }
    };
    private final byte[] data;

    public Hash(byte[] arrby) {
        this.data = arrby;
    }

    public Hash(Deserializer deserializer) {
        this.data = deserializer.j("data");
    }

    public static Hash a(String string) {
        try {
            return new Hash(g.getBytes(string));
        }
        catch (EncodingException var1_1) {
            throw new CryptoException(var1_1);
        }
    }

    public byte[] o() {
        return this.data;
    }

    public long p() {
        return e.i(this.data);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("data", this.o());
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof Hash)) {
            return false;
        }
        Hash hash = (Hash)object;
        return Arrays.equals(this.data, hash.data);
    }

    public String toString() {
        return g.h(this.data);
    }

}