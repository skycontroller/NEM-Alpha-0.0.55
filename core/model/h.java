package org.nem.core.model;

import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.c;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.c;

public abstract class h {
    public static Hash a(VerifiableEntity verifiableEntity) {
        return h.a(verifiableEntity.bi());
    }

    public static Hash a(SerializableEntity serializableEntity) {
        byte[] arrby = org.nem.core.serialization.c.c(serializableEntity);
        return new Hash(c.a(arrby));
    }

    public static Hash a(Hash hash, PublicKey publicKey) {
        return new Hash(c.a(hash.o(), publicKey.o()));
    }
}