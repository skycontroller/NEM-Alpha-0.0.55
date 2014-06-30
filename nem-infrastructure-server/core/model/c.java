package org.nem.core.model;

import java.util.List;
import org.nem.core.model.Account;
import org.nem.core.model.g;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.q;

public class c
implements q {
    private final List<g> aS;
    private final BlockHeight aD;
    private final boolean aT;

    public c(List<g> list, BlockHeight blockHeight, boolean bl) {
        this.aS = list;
        this.aD = blockHeight;
        this.aT = bl;
    }

    @Override
    public void a(Account account, Account account2, Amount amount) {
        this.a(account2, amount);
        this.b(account, amount);
    }

    @Override
    public void a(Account account, Amount amount) {
        for (g g : this.aS) {
            if (this.aT) {
                g.b(this.aD, account, amount);
                continue;
            }
            g.c(this.aD, account, amount);
        }
    }

    @Override
    public void b(Account account, Amount amount) {
        for (g g : this.aS) {
            if (this.aT) {
                g.a(this.aD, account, amount);
                continue;
            }
            g.d(this.aD, account, amount);
        }
    }
}