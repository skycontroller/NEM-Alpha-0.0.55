package org.nem.nis.t;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.nem.core.math.a;
import org.nem.nis.t.ew;
import org.nem.nis.t.fj;

public class ou {
    private static final Logger LOGGER = Logger.getLogger(fj.class.getName());

    public double a(List<Integer> list, a a, a a2) {
        double d = 0.0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int n = iterator.next();
            d+=a2.d(n) * a.d(n);
        }
        return d / (double)a2.size();
    }

    public a a(a a, a a2, a a3, ew ew) {
        ou.LOGGER.finer("outlinkVector" + a2);
        ou.LOGGER.finer("importanceVector" + a);
        ou.LOGGER.finer("vestedBalanceVector" + a3);
        a a4 = this.b(a, a2, a3, ew);
        a4.normalize();
        return a4;
    }

    private a b(a a, a a2, a a3, ew ew) {
        switch (ew) {
            case eM: {
                return this.a(a, a2, a3);
            }
            case eP: {
                return this.d(a, a2, a3);
            }
            case eQ: {
                return this.e(a, a2, a3);
            }
            case eR: {
                return this.f(a, a2, a3);
            }
            case eN: {
                return this.b(a, a2, a3);
            }
        }
        return this.c(a, a2, a3);
    }

    private a a(a a, a a2, a a3) {
        a a4 = a2.b(2.0).b(a3);
        a4.normalize();
        return a.a(a4);
    }

    private a b(a a, a a2, a a3) {
        a3.normalize();
        a2.normalize();
        double d = 0.5;
        double d2 = 0.05;
        a a4 = a2.b(d);
        a a5 = a.b(d2);
        return a3.b(a4).b(a5);
    }

    private a c(a a, a a2, a a3) {
        double d = 1.5;
        double d2 = 0.01;
        a a4 = a2.b(d).b(a3);
        a a5 = a.b(d2);
        a4.normalize();
        return a4.b(a5);
    }

    private a d(a a, a a2, a a3) {
        a2.normalize();
        a a4 = a2.b(2.0).b(a);
        a4.normalize();
        a a5 = a3.A();
        return a4.a(a3).b(a5);
    }

    private a e(a a, a a2, a a3) {
        a2.normalize();
        a3.normalize();
        return a.b(a2).a(a3);
    }

    private a f(a a, a a2, a a3) {
        double d = 1.25;
        double d2 = 0.05;
        a a4 = a2.b(d).b(a3);
        a a5 = a.b(d2);
        a4.normalize();
        return a4.b(a5);
    }
}