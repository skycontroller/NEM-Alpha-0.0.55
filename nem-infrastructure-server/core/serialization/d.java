package org.nem.core.serialization;

import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.serialization.b;

public class d {
    private final b cG;

    public d(b b) {
        this.cG = b;
    }

    public Account d(Address address) {
        return this.cG.c(address);
    }
}