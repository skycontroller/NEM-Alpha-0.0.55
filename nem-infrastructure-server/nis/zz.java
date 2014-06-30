package org.nem.nis;

import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.serialization.b;
import org.nem.nis.AccountAnalyzer;

class zz
implements b {
    private final AccountAnalyzer aO;

    public zz(AccountAnalyzer accountAnalyzer) {
        this.aO = accountAnalyzer;
    }

    @Override
    public Account c(Address address) {
        return this.aO.e(address);
    }

    @Override
    public boolean h(Address address) {
        return this.aO.h(address);
    }
}
