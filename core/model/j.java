package org.nem.core.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import org.nem.core.model.i;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class j {
    public final long bh = 2160;
    private final ArrayList<i> bi = new ArrayList<i>();

    public int size() {
        return this.bi.size();
    }

    public j aE() {
        j j = new j();
        if (this.size() <= 0) return j;
        BlockHeight blockHeight = this.bi.get(this.bi.size() - 1).ao();
        this.e(new BlockHeight(Math.max(1, blockHeight.bw() - 2160)));
        for (int i = 0; i < this.size(); ++i) {
            j.bi.add(i, new i(this.bi.get(i).ao(), this.bi.get(i).aj()));
        }
        return j;
    }

    public i a(BlockHeight blockHeight, BlockHeight blockHeight2) {
        int n;
        if (blockHeight.m(blockHeight2) > 2160 || blockHeight2.bw() < 1) {
            throw new InvalidParameterException("Historical balances are only available for the last 2160 blocks.");
        }
        if (blockHeight.a((a)blockHeight2) < 0) {
            throw new InvalidParameterException("Future historical balances are not known.");
        }
        if (this.bi.size() == 0) {
            return new i(new BlockHeight(blockHeight2.bw()), Amount.cs);
        }
        if ((n = Collections.binarySearch(this.bi, new i(blockHeight2, null))) == -1) {
            return new i(new BlockHeight(blockHeight2.bw()), new Amount(0));
        }
        if (n < -1) {
            n = - n - 2;
        }
        i i = this.bi.get(n);
        return new i(i.ao(), i.aj());
    }

    public Amount b(BlockHeight blockHeight, BlockHeight blockHeight2) {
        return this.a(blockHeight, blockHeight2).aj();
    }

    public void a(BlockHeight blockHeight, Amount amount) {
        Object object;
        int n = -1;
        int n2 = Collections.binarySearch(this.bi, new i(blockHeight, null));
        if (n2 < 0) {
            object = n2 == -1 ? Amount.cs : this.bi.get(- n2 - 2).aj();
            this.bi.add(- n2 - 1, new i(blockHeight, object.g(amount)));
            n = - n2;
        } else {
            this.bi.get(n2).c(amount);
            n = n2 + 1;
        }
        if (n < this.bi.size()) {
            object = this.bi.listIterator(n);
            while (object.hasNext()) {
                ((i)object.next()).c(amount);
            }
        }
        this.e(new BlockHeight(Math.max(1, blockHeight.bw() - 2160)));
    }

    public void b(BlockHeight blockHeight, Amount amount) {
        Object object;
        int n = -1;
        int n2 = Collections.binarySearch(this.bi, new i(blockHeight, null));
        if (n2 < 0) {
            object = n2 == -1 ? Amount.cs : this.bi.get(- n2 - 2).aj();
            this.bi.add(- n2 - 1, new i(blockHeight, object.h(amount)));
            n = - n2;
        } else {
            this.bi.get(n2).d(amount);
            n = n2 + 1;
        }
        if (n < this.bi.size()) {
            object = this.bi.listIterator(n);
            while (object.hasNext()) {
                ((i)object.next()).d(amount);
            }
        }
        this.e(new BlockHeight(Math.max(1, blockHeight.bw() - 2160)));
    }

    private void e(BlockHeight blockHeight) {
        int n;
        if (this.bi.size() == 0 || this.bi.get(0).ao().bw() >= blockHeight.bw()) {
            return;
        }
        i i = this.a(blockHeight, blockHeight);
        boolean bl = false;
        if ((n = Collections.binarySearch(this.bi, new i(blockHeight, null))) < 0) {
            n = - n - 1;
            bl = true;
        }
        if (n > 0) {
            ListIterator<i> listIterator = this.bi.listIterator();
            while (listIterator.hasNext() && n-- > 0) {
                listIterator.next();
                listIterator.remove();
            }
        }
        if (!bl) return;
        this.bi.add(0, i);
    }
}