package org.nem.nis;

import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.g;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.d;
import org.nem.nis.AccountAnalyzer;

public class a
implements g {
    final AccountAnalyzer aO;

    public a(AccountAnalyzer accountAnalyzer) {
        this.aO = accountAnalyzer;
    }

    @Override
    public void a(BlockHeight blockHeight22, Account blockHeight22, Amount blockHeight22) {
    }

    @Override
    public void b(BlockHeight blockHeight, Account account, Amount amount) {
        this.b(blockHeight, account);
    }

    @Override
    public void c(BlockHeight blockHeight22, Account blockHeight22, Amount blockHeight22) {
    }

    @Override
    public void d(BlockHeight blockHeight, Account account, Amount amount) {
        this.c(blockHeight, account);
    }

    private void b(BlockHeight blockHeight, Account account) {
        Account account2 = this.aO.c(account.ai());
        account2.a(blockHeight);
        account2.aq();
    }

    private void c(BlockHeight blockHeight, Account account) {
        Address address = account.ai();
        Account account2 = this.aO.c(address);
        if (null == account2 || null == account2.ao()) {
            throw new IllegalArgumentException("problem during undo, account not present in AA or account height is null");
        }
        if (!d.cz.equals((Object)account2.ar())) return;
        this.aO.f(address);
    }
}
