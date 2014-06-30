package org.nem.nis;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.g;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.r;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.a;
import org.nem.nis.gu;

public class yj {
    private static final Logger LOGGER = Logger.getLogger(yj.class.getName());
    private final AccountAnalyzer aO;
    private final int do;
    private final gu dp;

    public yj(AccountAnalyzer accountAnalyzer, gu gu, int n) {
        this.aO = accountAnalyzer;
        this.dp = gu;
        this.do = n;
    }

    public boolean a(Block block, Collection<Block> collection) {
        if (collection.size() > this.do) {
            return false;
        }
        a a = new a(this.aO);
        BlockHeight blockHeight = block.ao().bz();
        for (Block block2 : collection) {
            block2.a(block);
            if (!(blockHeight.equals((Object)block2.ao()) && block2.verify())) {
                return false;
            }
            if (!this.b(block, block2)) {
                yj.LOGGER.fine(String.format("hit failed on block %s gen %s", block2.ao(), block2.getGenerationHash()));
                return false;
            }
            for (Transaction transaction : block2.aA()) {
                if (r.bS == transaction.aY() && transaction.verify()) continue;
                return false;
            }
            block = block2;
            blockHeight = blockHeight.bz();
            block2.a(a);
            block2.execute();
            block2.b(a);
        }
        return true;
    }

    private boolean b(Block block, Block block2) {
        BigInteger bigInteger = this.dp.i(block2);
        BigInteger bigInteger2 = this.dp.c(block, block2);
        return bigInteger.compareTo(bigInteger2) < 0;
    }
}
