package org.nem.core.model;

import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;

public class o {
    public static final ObjectDeserializer<Transaction> bf = deserializer -> o.b(VerifiableEntity.a.cc, deserializer);
    public static final ObjectDeserializer<Transaction> bg = deserializer -> o.b(VerifiableEntity.a.cd, deserializer);

    private static Transaction b(VerifiableEntity.a a, Deserializer deserializer) {
        int n = deserializer.f("type");
        switch (n) {
            case 257: {
                return new TransferTransaction(a, deserializer);
            }
        }
        throw new IllegalArgumentException("Unknown transaction type: " + n);
    }
}