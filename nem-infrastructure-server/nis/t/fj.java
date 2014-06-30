package org.nem.nis.t;

import java.util.Collection;
import java.util.logging.Logger;
import org.nem.core.math.a;
import org.nem.core.model.Account;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.t.ew;
import org.nem.nis.t.gm;
import org.nem.nis.t.ou;
import org.nem.nis.t.wj;
import org.nem.nis.t.zt;

public class fj
implements gm {
    private static final Logger LOGGER = Logger.getLogger(fj.class.getName());
    public static final int DEFAULT_MAX_ITERATIONS = 200;
    public static final double ew = 0.001;

    @Override
    public void a(BlockHeight blockHeight, Collection<Account> collection, ew ew) {
        zt zt = new zt((Iterable<Account>)collection, blockHeight);
        ou ou = new ou();
        wj wj = new wj(zt, ou, collection.size());
        long l = System.currentTimeMillis();
        wj.run();
        long l2 = System.currentTimeMillis();
        fj.LOGGER.info("POI iterator needed " + (l2 - l) + "ms.");
        if (!wj.dl()) {
            String string = String.format("POI: power iteration failed to converge in %s iterations", 200);
            throw new IllegalStateException(string);
        }
        a a = ou.a(wj.dm(), zt.dc(), zt.db(), ew);
        zt.b(wj.dm(), a);
    }
}