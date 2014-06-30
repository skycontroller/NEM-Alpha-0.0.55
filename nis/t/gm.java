package org.nem.nis.t;

import java.util.Collection;
import org.nem.core.model.Account;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.t.ew;

public interface gm {
    default public void a(BlockHeight blockHeight, Collection<Account> collection) {
        this.a(blockHeight, collection, ew.eR);
    }

    public void a(BlockHeight var1, Collection<Account> var2, ew var3);
}