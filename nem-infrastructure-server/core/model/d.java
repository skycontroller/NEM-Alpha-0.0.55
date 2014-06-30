package org.nem.core.model;

import java.util.List;
import org.nem.core.model.Account;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.q;

public class d
implements q {
    private final List<q> aU;

    public d(List<q> list) {
        this.aU = list;
    }

    @Override
    public void a(Account account, Account account2, Amount amount) {
        for (q q : this.aU) {
            q.a(account, account2, amount);
        }
    }

    @Override
    public void a(Account account, Amount amount) {
        for (q q : this.aU) {
            q.a(account, amount);
        }
    }

    @Override
    public void b(Account account, Amount amount) {
        for (q q : this.aU) {
            q.b(account, amount);
        }
    }
}