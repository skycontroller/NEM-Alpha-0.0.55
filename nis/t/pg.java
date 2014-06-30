package org.nem.nis.t;

import org.nem.core.model.Address;

public class pg {
    private final double eY;
    private final Address aN;

    public pg(Address address, double d) {
        this.eY = d;
        this.aN = address;
    }

    public double getWeight() {
        return this.eY;
    }

    public Address aw() {
        return this.aN;
    }

    public int hashCode() {
        return (int)this.getWeight() ^ this.aN.ax().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof pg)) {
            return false;
        }
        pg pg = (pg)object;
        return this.eY == pg.eY && this.aN.equals(pg.aN);
    }

    public String toString() {
        return String.format("%s -> %s", this.eY, this.aN);
    }
}