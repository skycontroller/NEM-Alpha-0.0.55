package org.nem.nis.t;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;
import org.nem.core.model.Account;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.a;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;
import org.nem.core.model.s;
import org.nem.nis.t.pg;

public class ja {
    private static final Amount eq = Amount.a(1000);
    public static final double er = 0.9;
    private final int index;
    private final Account es;
    private final BlockHeight aD;
    private final Map<Address, Double> et = new HashMap<Address, Double>();
    private final List<pg> eu = new ArrayList<pg>();

    public ja(int n, Account account, BlockHeight blockHeight) {
        this.index = n;
        this.es = account;
        this.aD = blockHeight;
        AccountImportance accountImportance = this.es.at();
        Iterator<a> iterator = accountImportance.b(blockHeight);
        while (iterator.hasNext()) {
            a a = iterator.next();
            long l = blockHeight.m(a.ao());
            long l2 = l / 1440;
            double d = l < 0 ? 0.0 : (double)a.av().bs() * Math.pow(0.9, l2);
            this.eu.add(new pg(a.aw(), d));
            this.a(a.aw(), d);
        }
    }

    private void a(Address address, double d) {
        this.et.put(address, this.et.getOrDefault(address, 0.0) + d);
    }

    public int getIndex() {
        return this.index;
    }

    public Account cW() {
        return this.es;
    }

    public boolean cX() {
        return this.es.as().i(this.aD).a((org.nem.core.model.primitive.a)ja.eq) >= 0 && this.es.aj().a((org.nem.core.model.primitive.a)ja.eq) >= 0;
    }

    public void a(pg pg) {
        this.a(pg.aw(), - pg.getWeight());
    }

    public List<pg> cY() {
        return this.eu;
    }

    public List<pg> cZ() {
        ArrayList<pg> arrayList = new ArrayList<pg>();
        for (Map.Entry<Address, Double> entry : this.et.entrySet()) {
            if (entry.getValue() <= 0.0) continue;
            arrayList.add(new pg(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    public double da() {
        return this.cZ().stream().map(pg -> pg.getWeight()).reduce(0.0, (arg_0, arg_1) -> Double.sum(arg_0, arg_1));
    }
}