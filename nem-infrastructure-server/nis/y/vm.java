package org.nem.nis.y;

import java.util.HashMap;
import java.util.Map;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.j.nn;
import org.nem.nis.y.yq;

public class vm
implements yq {
    private final nn cU;
    private final Map<String, Account> ep;

    public vm(nn nn) {
        this.cU = nn;
        this.ep = new HashMap<String, Account>();
    }

    @Override
    public Account j(Address address) {
        String string = address.ax();
        Account account = this.ep.get(string);
        boolean bl = null != account;
        if (!bl) {
            account = this.cU.v(string);
        }
        if (null == account) {
            account = new Account(string, null);
        }
        if (null == account.getPublicKey()) {
            account.setPublicKey(address.getPublicKey());
        }
        if (bl) return account;
        this.ep.put(string, account);
        return account;
    }
}