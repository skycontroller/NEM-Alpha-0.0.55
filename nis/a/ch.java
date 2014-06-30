package org.nem.nis.a;

import java.util.logging.Logger;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.f;
import org.nem.core.model.o;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.nis.a.g.jf;
import org.nem.nis.d.ns;
import org.nem.peer.SecureSerializableEntity;
import org.nem.peer.node.NodeIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ch {
    private static final Logger LOGGER = Logger.getLogger(ch.class.getName());
    private final ns ee;

    @Autowired(required=1)
    public ch(ns ns) {
        this.ee = ns;
    }

    @RequestMapping(value={"/push/transaction"}, method={RequestMethod.POST})
    @jf
    public void g(@RequestBody Deserializer deserializer) {
        ch.LOGGER.info("[start] /push/transaction");
        SecureSerializableEntity<Transaction> secureSerializableEntity = new SecureSerializableEntity<Transaction>(deserializer, o.bf);
        this.ee.a(secureSerializableEntity.dV(), secureSerializableEntity.dW());
        ch.LOGGER.info("[end] /push/transaction recipient:" + ((TransferTransaction)secureSerializableEntity.dV()).ba().ai().ax() + " signer:" + secureSerializableEntity.dV().be());
    }

    @RequestMapping(value={"/push/block"}, method={RequestMethod.POST})
    @jf
    public void h(@RequestBody Deserializer deserializer) {
        ch.LOGGER.info("[start] /push/block");
        SecureSerializableEntity<Block> secureSerializableEntity = new SecureSerializableEntity<Block>(deserializer, f.bf);
        this.ee.a(secureSerializableEntity.dV(), secureSerializableEntity.dW());
        ch.LOGGER.info("[end] /push/block height:" + secureSerializableEntity.dV().ao() + " signer:" + secureSerializableEntity.dV().be());
    }
}
