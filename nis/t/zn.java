package org.nem.nis.t;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.nem.core.math.a;
import org.nem.core.math.b;
import org.nem.core.model.Account;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.s;
import org.nem.nis.t.ja;
import org.nem.nis.t.pg;

class zn {
    private final BlockHeight aD;
    private final List<Integer> eA;
    private final a eB;
    private final a eC;
    private final a hO;
    private final a eE;
    private b eF;
    private final List<ja> eI = new ArrayList<ja>();
    private final Map<Address, ja> eJ = new HashMap<Address, ja>();
    private final Map<Address, Integer> eK = new HashMap<Address, Integer>();

    public zn(Iterable<Account> iterable, BlockHeight blockHeight) {
        this.aD = blockHeight;
        this.eA = new ArrayList<Integer>();
        int n = 0;
        Iterator<Account> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Account account;
            ja ja;
            if (!(ja = new ja(n, account = iterator.next(), blockHeight)).cX()) continue;
            this.eJ.put(account.ai(), ja);
            this.eK.put(account.ai(), n);
            this.eI.add(ja);
            ++n;
        }
        if (0 == n) {
            throw new IllegalArgumentException("there aren't any foraging eligible accounts");
        }
        this.eB = new a(n);
        this.eB.c(1.0);
        this.eC = new a(n);
        this.hO = new a(n);
        this.eE = new a(n);
    }

    public void process() {
        int n = 0;
        int n2 = 0;
        for (ja ja : this.eI) {
            Account account = ja.cW();
            n2+=account.at().c(this.aD);
            this.eC.a(n, account.as().i(this.aD).bs());
            ++n;
        }
        this.eF = new b(n, n, n2 < n ? 1 : n2 / n);
        this.dk();
        this.fi();
    }

    public void b(a a, a a2) {
        if (a.size() != this.eI.size()) {
            throw new IllegalArgumentException("page rank vector is an unexpected dimension");
        }
        if (a2.size() != this.eI.size()) {
            throw new IllegalArgumentException("importance vector is an unexpected dimension");
        }
        int n = 0;
        for (ja ja : this.eI) {
            AccountImportance accountImportance = ja.cW().at();
            accountImportance.h(a.d(n));
            accountImportance.a(this.aD, a2.d(n));
            ++n;
        }
    }

    private void fi() {
        int n = 0;
        for (ja ja : this.eI) {
            AccountImportance accountImportance = ja.cW().at();
            this.hO.a(n, accountImportance.bX());
            ++n;
        }
        if (this.hO.C()) {
            this.hO.c(1.0);
        }
        this.hO.normalize();
    }

    private void dk() {
        Object object;
        pg pg;
        for (ja ja22 : this.eI) {
            Iterator<pg> iterator = ja22.cY().iterator();
            while (iterator.hasNext()) {
                if (null == (object = this.eJ.get((pg = iterator.next()).aw()))) continue;
                object.a(new pg(ja22.cW().ai(), pg.getWeight()));
            }
        }
        for (ja ja22 : this.eI) {
            Iterator<pg> iterator = ja22.cZ().iterator();
            while (iterator.hasNext()) {
                if (null == (object = this.eK.get((pg = iterator.next()).aw()))) continue;
                this.eF.b(object.intValue(), ja22.getIndex(), pg.getWeight());
            }
            int n = ja22.getIndex();
            if (0.0 == (pg = (pg)ja22.da())) {
                this.eA.add(n);
                continue;
            }
            this.eE.a(n, (double)pg);
        }
        this.eF.M();
        this.eF.L();
    }

    static /* synthetic */ a c(zn zn) {
        return zn.eC;
    }

    static /* synthetic */ a d(zn zn) {
        return zn.eE;
    }

    static /* synthetic */ a e(zn zn) {
        return zn.hO;
    }

    static /* synthetic */ List a(zn zn) {
        return zn.eA;
    }

    static /* synthetic */ a b(zn zn) {
        return zn.eB;
    }

    static /* synthetic */ b f(zn zn) {
        return zn.eF;
    }
}