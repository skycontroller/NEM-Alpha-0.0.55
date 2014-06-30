package org.nem.core.model.primitive;

public abstract class a<TDerived extends a<?, TValue>, TValue extends Number>
implements Comparable<TDerived> {
    private final TValue cp;
    private final Class<TDerived> cq;

    protected a(TValue TValue, Class<TDerived> class_) {
        this.cp = TValue;
        this.cq = class_;
    }

    public int a(TDerived TDerived) {
        return ((Comparable)this.cp).compareTo(TDerived.getValue());
    }

    protected TValue getValue() {
        return this.cp;
    }

    public int hashCode() {
        long l = this.cp.longValue();
        return (int)(l ^ l >> 32);
    }

    public boolean equals(Object object) {
        a a;
        if (!this.cq.isInstance(object)) {
            return false;
        }
        return 0 == this.a(a = (a)this.cq.cast(object));
    }

    public String toString() {
        return this.cp.toString();
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((a)object);
    }
}