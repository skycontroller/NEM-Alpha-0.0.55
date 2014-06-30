package org.nem.nis;

import org.nem.core.model.Account;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.q;
import org.nem.nis.UnconfirmedTransactions;

class jl
implements q {
    final /* synthetic */ UnconfirmedTransactions dZ;

    private jl(UnconfirmedTransactions unconfirmedTransactions) {
        this.dZ = unconfirmedTransactions;
    }

    @Override
    public void a(Account account, Account account2, Amount amount) {
        this.b(account, amount);
        this.a(account2, amount);
    }

    @Override
    public void a(Account account, Amount amount) {
        UnconfirmedTransactions.a(this.dZ, account);
        Amount amount2 = ((Amount)UnconfirmedTransactions.a(this.dZ).get((Object)account)).g(amount);
        UnconfirmedTransactions.a(this.dZ).replace(account, amount2);
    }

    @Override
    public void b(Account account, Amount amount) {
        UnconfirmedTransactions.a(this.dZ, account);
        Amount amount2 = ((Amount)UnconfirmedTransactions.a(this.dZ).get((Object)account)).h(amount);
        UnconfirmedTransactions.a(this.dZ).replace(account, amount2);
    }

    /* synthetic */ jl(UnconfirmedTransactions unconfirmedTransactions, UnconfirmedTransactions.1 varnull) {
        this(unconfirmedTransactions);
    }
}
