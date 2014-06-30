package org.nem.nis.d;

import java.util.Collection;
import java.util.MissingResourceException;
import org.nem.core.model.Account;
import org.nem.core.model.Block;
import org.nem.nis.d.lx;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.j.cf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class xn
implements lx {
    private final cf cX;

    @Autowired(required=1)
    public xn(cf cf) {
        this.cX = cf;
    }

    @Override
    public Long cV() {
        return this.cX.cV();
    }

    @Override
    public Transfer l(byte[] arrby) {
        Transfer transfer = this.cX.l(arrby);
        if (null != transfer) return transfer;
        throw xn.x(arrby.toString());
    }

    @Override
    public Collection<Object[]> b(Account account, Integer n, int n2) {
        Collection collection = this.cX.b(account, n, n2);
        return collection;
    }

    private static MissingResourceException x(String string) {
        return new MissingResourceException("block not found in the db", Block.class.getName(), string);
    }
}