package org.nem.nis.j;

import java.util.Collection;
import org.nem.core.model.Account;
import org.nem.nis.dbmodel.Transfer;

public interface au {
    public Long cV();

    public Transfer l(byte[] var1);

    public Collection<Object[]> b(Account var1, Integer var2, int var3);
}