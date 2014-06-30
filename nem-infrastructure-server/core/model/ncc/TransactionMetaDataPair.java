package org.nem.core.model.ncc;

import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.o;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class TransactionMetaDataPair
implements SerializableEntity {
    private Transaction cn;
    private TransactionMetaData co;

    public TransactionMetaDataPair(Transaction transaction, TransactionMetaData transactionMetaData) {
        this.cn = transaction;
        this.co = transactionMetaData;
    }

    public TransactionMetaDataPair(Deserializer deserializer) {
        this(deserializer.a("transaction", o.bf), deserializer.a("meta", deserializer -> new TransactionMetaData(deserializer)));
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("transaction", this.cn);
        serializer.a("meta", this.co);
    }

    public Transaction bq() {
        return this.cn;
    }

    public TransactionMetaData br() {
        return this.co;
    }
}