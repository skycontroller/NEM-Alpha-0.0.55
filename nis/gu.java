package org.nem.nis;

import java.math.BigInteger;
import java.util.List;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Block;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.time.TimeInstant;
import org.nem.nis.AccountAnalyzer;

public class gu {
    private static final long dq = 60;
    public static final BigInteger dr = new BigInteger("18446744073709551616");
    public static final long ds = 0x40000000000000L;
    public static final int dt = 60;
    private static final double du = Math.pow(2.0, 256.0);
    private static final int hs = 31;
    public final AccountAnalyzer aO;

    public gu(AccountAnalyzer accountAnalyzer) {
        this.aO = accountAnalyzer;
    }

    public void cn() {
        this.aO.bZ();
    }

    public BigInteger i(Block block) {
        BigInteger bigInteger = new BigInteger(1, block.getGenerationHash().o());
        double d = Math.abs(Math.log(bigInteger.doubleValue() / gu.du));
        bigInteger = BigInteger.valueOf((long)(1.8014398509481984E16 * d));
        return bigInteger;
    }

    public BigInteger c(Block block, Block block2) {
        int n = block2.bf().b(block.bf());
        if (n < 0) {
            return BigInteger.ZERO;
        }
        long l = this.j(block2);
        return BigInteger.valueOf(n).multiply(BigInteger.valueOf(l)).multiply(gu.dr).divide(block2.aB().bx());
    }

    public BlockHeight w(BlockHeight blockHeight) {
        long l = blockHeight.bw() - 1;
        long l2 = l / 31 * 31;
        return new BlockHeight(l2 + 1);
    }

    public long j(Block block) {
        BlockHeight blockHeight = this.w(block.ao());
        this.aO.o(blockHeight);
        long l = NemesisBlock.bn.bt();
        return (long)(block.be().at().d(blockHeight) * (double)l);
    }

    public long d(Block block, Block block2) {
        int n = block2.bf().b(block.bf());
        return this.a(n, block2.aB().bw());
    }

    private long a(int n, long l) {
        return l - (long)n;
    }

    public c a(List<c> list, List<TimeInstant> list2) {
        long l;
        long l2;
        if (list.size() < 2) {
            return c.cv;
        }
        TimeInstant timeInstant = list2.get(list2.size() - 1);
        TimeInstant timeInstant2 = list2.get(0);
        long l3 = timeInstant.b(timeInstant2);
        long l4 = list.size();
        long l5 = 0;
        for (c c : list) {
            l5+=c.bw();
        }
        if (19 * (l2 = list.get(list.size() - 1).bw()) > 20 * (l = BigInteger.valueOf(l5/=l4).multiply(BigInteger.valueOf(60)).multiply(BigInteger.valueOf(l4)).divide(BigInteger.valueOf(l3)).longValue())) {
            l = 19 * l2 / 20;
        } else {
            if (21 * l2 >= 20 * l) return new c(l);
            l = 21 * l2 / 20;
        }
        return new c(l);
    }
}
