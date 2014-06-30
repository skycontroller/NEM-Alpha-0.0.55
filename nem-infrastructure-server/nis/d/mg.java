package org.nem.nis.d;

import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.SerializableList;

public interface mg
extends Iterable<Account> {
    public Account c(Address var1);

    public SerializableList<TransactionMetaDataPair> a(Address var1, String var2);

    public SerializableList<Block> b(Address var1, String var2);

    public SerializableList<HarvestInfo> c(Address var1, String var2);
}