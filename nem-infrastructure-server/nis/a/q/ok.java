package org.nem.nis.a.q;

import org.nem.core.model.Address;

public class ok {
    private final Address ax;
    private final String ef;

    public ok(String string, String string2) {
        if (null == string) {
            throw new IllegalArgumentException("address is required");
        }
        this.ax = Address.e(string);
        if (!this.ax.isValid()) {
            throw new IllegalArgumentException("address must be valid");
        }
        this.ef = string2;
    }

    public Address ai() {
        return this.ax;
    }

    public String getTimestamp() {
        return this.ef;
    }
}