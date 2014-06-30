package org.nem.nis.a.q;

import org.nem.nis.a.q.ok;

public class qi {
    private String address;
    private String ef;

    public void setAddress(String string) {
        this.address = string;
    }

    public void u(String string) {
        this.ef = string;
    }

    public ok cL() {
        return new ok(this.address, this.ef);
    }
}
