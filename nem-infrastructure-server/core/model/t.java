package org.nem.core.model;

import org.nem.core.model.Account;
import org.nem.core.model.g;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.s;

public class t
implements g {
    @Override
    public void a(BlockHeight blockHeight, Account account, Amount amount) {
        account.as().j(blockHeight, amount);
    }

    @Override
    public void b(BlockHeight blockHeight, Account account, Amount amount) {
        account.as().h(blockHeight, amount);
    }

    @Override
    public void c(BlockHeight blockHeight, Account account, Amount amount) {
        account.as().k(blockHeight, amount);
    }

    @Override
    public void d(BlockHeight blockHeight, Account account, Amount amount) {
        account.as().i(blockHeight, amount);
    }
}