package org.nem.nis.d;

import java.util.Collection;
import java.util.MissingResourceException;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Account;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.d.dl;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.j.zh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class su
implements dl {
    private final zh cV;

    @Autowired(required=1)
    public su(zh zh) {
        this.cV = zh;
    }

    @Override
    public Long cV() {
        return this.cV.cV();
    }

    @Override
    public org.nem.nis.dbmodel.Block f(long l) {
        org.nem.nis.dbmodel.Block block = this.cV.f(l);
        if (null != block) return block;
        throw su.x(Long.toBinaryString(l));
    }

    @Override
    public org.nem.nis.dbmodel.Block f(Hash hash) {
        org.nem.nis.dbmodel.Block block = this.cV.f(hash);
        if (null != block) return block;
        throw su.x(hash.toString());
    }

    @Override
    public org.nem.nis.dbmodel.Block s(BlockHeight blockHeight) {
        org.nem.nis.dbmodel.Block block = this.cV.s(blockHeight);
        if (null != block) return block;
        throw su.x(blockHeight.toString());
    }

    @Override
    public HashChain c(BlockHeight blockHeight, int n) {
        return this.cV.c(blockHeight, n);
    }

    @Override
    public Collection<org.nem.nis.dbmodel.Block> a(Account account, Integer n, int n2) {
        return this.cV.a(account, n, n2);
    }

    private static MissingResourceException x(String string) {
        return new MissingResourceException("block not found in the db", Block.class.getName(), string);
    }
}