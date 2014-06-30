package org.nem.nis.j;

import java.util.Collection;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Account;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.dbmodel.Block;

public interface vs {
    public Long cV();

    public Block f(long var1);

    public Block f(Hash var1);

    public Block s(BlockHeight var1);

    public HashChain c(BlockHeight var1, int var2);

    public Collection<Block> a(Account var1, Integer var2, int var3);
}