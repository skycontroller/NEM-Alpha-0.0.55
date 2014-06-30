package org.nem.core.model;

import java.math.BigInteger;
import org.nem.core.model.Account;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.a;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.q;
import org.nem.core.model.s;

public class n
implements q {
    private final BlockHeight aD;
    private final boolean aT;

    public n(BlockHeight blockHeight, boolean bl) {
        this.aD = blockHeight;
        this.aT = bl;
    }

    @Override
    public void a(Account account, Account account2, Amount amount) {
        if (account.ai().equals(account2.ai())) {
            return;
        }
        Amount amount2 = this.c(this.aT ? account : account2, amount);
        if (this.aT) {
            a a = new a(this.aD, amount2, account2.ai());
            account.at().a(a);
        } else {
            a a = new a(this.aD, amount2, account.ai());
            account2.at().b(a);
        }
    }

    private Amount c(Account account, Amount amount) {
        s s = account.as();
        BigInteger bigInteger = BigInteger.valueOf(n.e(s.i(this.aD)));
        BigInteger bigInteger2 = BigInteger.valueOf(n.e(s.j(this.aD)));
        if (bigInteger2.compareTo(BigInteger.ZERO) <= 0) {
            return amount;
        }
        long l = BigInteger.valueOf(amount.bs()).multiply(bigInteger).divide(bigInteger.add(bigInteger2)).longValue();
        return Amount.b(l);
    }

    private static long e(Amount amount) {
        return null == amount ? 0 : amount.bs();
    }

    @Override
    public void a(Account account2, Amount account2) {
    }

    @Override
    public void b(Account account2, Amount account2) {
    }
}