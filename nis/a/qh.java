package org.nem.nis.a;

import java.util.logging.Logger;
import org.nem.core.crypto.e;
import org.nem.core.model.RequestAnnounce;
import org.nem.core.model.RequestPrepare;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.o;
import org.nem.core.model.r;
import org.nem.core.serialization.BinaryDeserializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.b;
import org.nem.core.serialization.c;
import org.nem.core.serialization.d;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.a.g.jv;
import org.nem.nis.d.ns;
import org.nem.peer.node.NodeIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class qh {
    private static final Logger LOGGER = Logger.getLogger(qh.class.getName());
    private final AccountAnalyzer aO;
    private final ns ee;

    @Autowired(required=1)
    qh(AccountAnalyzer accountAnalyzer, ns ns) {
        this.aO = accountAnalyzer;
        this.ee = ns;
    }

    @RequestMapping(value={"/transfer/prepare"}, method={RequestMethod.POST})
    @jv
    public RequestPrepare i(@RequestBody Deserializer deserializer) {
        TransferTransaction transferTransaction = qh.j(deserializer);
        r r = transferTransaction.aY();
        if (r.bS != transferTransaction.aY()) {
            throw new IllegalArgumentException(r.toString());
        }
        byte[] arrby = c.c(transferTransaction.bi());
        return new RequestPrepare(arrby);
    }

    @RequestMapping(value={"/transfer/announce"}, method={RequestMethod.POST})
    @jv
    public void a(@RequestBody RequestAnnounce requestAnnounce) throws Exception {
        TransferTransaction transferTransaction = this.k(requestAnnounce.getData());
        transferTransaction.a(new e(requestAnnounce.getSignature()));
        this.ee.a(transferTransaction, (NodeIdentity)null);
    }

    private TransferTransaction k(byte[] arrby) throws Exception {
        BinaryDeserializer binaryDeserializer = qh.a(arrby, this.aO);
        Throwable throwable = null;
        try {
            TransferTransaction transferTransaction = qh.j(binaryDeserializer);
            return transferTransaction;
        }
        catch (Throwable var4_5) {
            throwable = var4_5;
            throw var4_5;
        }
        finally {
            if (binaryDeserializer != null) {
                if (throwable != null) {
                    try {
                        binaryDeserializer.close();
                    }
                    catch (Throwable var5_6) {
                        throwable.addSuppressed(var5_6);
                    }
                } else {
                    binaryDeserializer.close();
                }
            }
        }
    }

    private static TransferTransaction j(Deserializer deserializer) {
        return (TransferTransaction)o.bg.deserialize(deserializer);
    }

    private static BinaryDeserializer a(byte[] arrby, b b) {
        return new BinaryDeserializer(arrby, new d(b));
    }
}
