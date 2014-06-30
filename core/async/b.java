package org.nem.core.async;

import java.util.List;
import org.nem.core.async.a;

public class b
extends a {
    private final List<a> c;
    private int d;

    public b(List<a> list) {
        this.c = list;
    }

    @Override
    public boolean a() {
        while (this.d < this.c.size()) {
            if (!this.b().a()) {
                return false;
            }
            ++this.d;
        }
        return true;
    }

    @Override
    protected int a(int n) {
        return this.b().next();
    }

    private a b() {
        return this.c.get(this.d);
    }
}