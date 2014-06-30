package org.nem.nis.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.SerializableList;
import org.nem.core.time.TimeInstant;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.d.dl;
import org.nem.nis.d.lx;
import org.nem.nis.d.mg;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.y.ap;
import org.nem.nis.y.lt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountIoAdapter
implements mg {
    private final lx eZ;
    private final dl ed;
    private final AccountAnalyzer aO;

    @Autowired(required=1)
    public AccountIoAdapter(lx lx, dl dl, AccountAnalyzer accountAnalyzer) {
        this.eZ = lx;
        this.ed = dl;
        this.aO = accountAnalyzer;
    }

    @Override
    public Account c(Address address) {
        return this.aO.c(address);
    }

    private Integer w(String string) {
        Integer n;
        if (string == null) {
            return Integer.MAX_VALUE;
        }
        try {
            n = Integer.valueOf(string, 10);
        }
        catch (NumberFormatException var3_2) {
            n = Integer.MAX_VALUE;
        }
        return n;
    }

    @Override
    public SerializableList<TransactionMetaDataPair> a(Address address, String string) {
        Account account = this.aO.c(address);
        Integer n = this.w(string);
        Collection collection = this.eZ.b(account, n, 25);
        SerializableList serializableList = new SerializableList(0);
        collection.stream().map(arrobject -> new TransactionMetaDataPair(ap.a((Transfer)arrobject[0], this.aO), new TransactionMetaData(new BlockHeight((Long)arrobject[1])))).forEach(transactionMetaDataPair -> {
            serializableList.f(transactionMetaDataPair);
        }
        );
        return serializableList;
    }

    @Override
    public SerializableList<Block> b(Address address, String string) {
        Account account = this.aO.c(address);
        Integer n = this.w(string);
        Collection collection = this.ed.a(account, n, 25);
        SerializableList serializableList = new SerializableList(0);
        collection.stream().forEach(block -> {
            block.setBlockTransfers(new LinkedList());
        }
        );
        collection.stream().map(block -> lt.a(block, this.aO)).forEach(block -> {
            serializableList.f(block);
        }
        );
        return serializableList;
    }

    @Override
    public SerializableList<HarvestInfo> c(Address address, String string) {
        Account account = this.aO.c(address);
        Integer n = this.w(string);
        Collection collection = this.ed.a(account, n, 25);
        SerializableList serializableList = new SerializableList(0);
        collection.stream().map(block -> new HarvestInfo(block.getBlockHash(), new BlockHeight(block.getHeight()), new TimeInstant(block.getTimestamp()), Amount.b(block.getTotalFee()))).forEach(harvestInfo -> {
            serializableList.f(harvestInfo);
        }
        );
        return serializableList;
    }

    @Override
    public Iterator<Account> iterator() {
        return this.aO.iterator();
    }
}
