package org.nem.core.time;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public class TimeInstant
implements Comparable<TimeInstant> {
    private final int cN;
    public static final TimeInstant cO = new TimeInstant(0);

    public TimeInstant(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("time must be non-negative");
        }
        this.cN = n;
    }

    public TimeInstant k(int n) {
        return new TimeInstant(this.cN + n);
    }

    public TimeInstant l(int n) {
        return this.k(60 * n);
    }

    public TimeInstant m(int n) {
        return this.l(60 * n);
    }

    public TimeInstant n(int n) {
        return this.m(24 * n);
    }

    public int b(TimeInstant timeInstant) {
        return this.cN - timeInstant.cN;
    }

    public int c(TimeInstant timeInstant) {
        return Integer.compare(this.cN, timeInstant.cN);
    }

    public int bI() {
        return this.cN;
    }

    public int hashCode() {
        return this.cN;
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof TimeInstant)) {
            return false;
        }
        TimeInstant timeInstant = (TimeInstant)object;
        return this.cN == timeInstant.cN;
    }

    public String toString() {
        return String.format("%d", this.cN);
    }

    public static void a(Serializer serializer, String string, TimeInstant timeInstant) {
        serializer.b(string, timeInstant.bI());
    }

    public static TimeInstant h(Deserializer deserializer, String string) {
        return new TimeInstant(deserializer.f(string));
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.c((TimeInstant)object);
    }
}