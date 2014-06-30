package org.nem.core.time;

import java.util.Calendar;
import java.util.TimeZone;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;

public class a
implements b {
    private static final long cL;
    private static final long cM;

    @Override
    public TimeInstant bG() {
        return TimeInstant.cO;
    }

    @Override
    public TimeInstant ac() {
        long l = System.currentTimeMillis();
        return new TimeInstant(a.d(l));
    }

    public static long bH() {
        return a.cL;
    }

    public static int d(long l) {
        return (int)((l - a.cM) / 1000);
    }

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(0, 1);
        calendar.set(1, 2014);
        calendar.set(2, 5);
        calendar.set(5, 4);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        a.cL = calendar.getTimeInMillis();
        a.cM = a.cL - 500;
    }
}