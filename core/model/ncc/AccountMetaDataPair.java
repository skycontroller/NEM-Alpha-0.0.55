package org.nem.core.model.ncc;

import org.nem.core.model.Account;
import org.nem.core.model.ncc.AccountMetaData;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class AccountMetaDataPair
implements SerializableEntity {
    private Account es;
    private AccountMetaData ho;

    public AccountMetaDataPair(Account account, AccountMetaData accountMetaData) {
        this.es = account;
        this.ho = accountMetaData;
    }

    public AccountMetaDataPair(Deserializer deserializer) {
        this(deserializer.a("account", deserializer -> new Account(deserializer)), deserializer.a("meta", deserializer -> new AccountMetaData(deserializer)));
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("account", this.es);
        serializer.a("meta", this.ho);
    }

    public Account cW() {
        return this.es;
    }

    public AccountMetaData cv() {
        return this.ho;
    }
}