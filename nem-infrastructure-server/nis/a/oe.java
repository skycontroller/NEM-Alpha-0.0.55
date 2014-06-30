package org.nem.nis.a;

import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.crypto.e;
import org.nem.core.crypto.f;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.Message;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.serialization.SerializableList;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;
import org.nem.core.utils.b;
import org.nem.core.utils.c;
import org.nem.core.utils.e;
import org.nem.core.utils.h;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.BlockChain;
import org.nem.nis.NisAsyncTimerVisitor;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.a.g.th;
import org.nem.nis.audit.AuditCollection;
import org.nem.nis.controller.viewmodels.BlockDebugInfo;
import org.nem.nis.controller.viewmodels.TransactionDebugInfo;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.gn;
import org.nem.nis.gu;
import org.nem.nis.j.zh;
import org.nem.nis.y.lt;
import org.nem.peer.PeerNetwork;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class oe {
    private static final Logger LOGGER = Logger.getLogger(oe.class.getName());
    private final NisPeerNetworkHost ec;
    private final BlockChain dB;
    private final zh cV;

    @Autowired(required=1)
    public oe(NisPeerNetworkHost nisPeerNetworkHost, BlockChain blockChain, zh zh) {
        this.ec = nisPeerNetworkHost;
        this.dB = blockChain;
        this.cV = zh;
    }

    @RequestMapping(value={"/debug/fix-node"}, method={RequestMethod.GET})
    public String s(@RequestParam(value="data") String string) {
        byte[] arrby = org.nem.core.utils.b.c(h.getBytes(this.ec.ct().dC().ea().eh().toString()), org.nem.core.utils.e.o(gn.o.ac().bI() / 60));
        f f = new f(new d(NemesisBlock.bm.getPublicKey()));
        byte[] arrby2 = org.nem.core.utils.c.getBytes(string);
        oe.LOGGER.info(String.format("%d %s", gn.o.ac().bI() / 60, this.ec.ct().dC().ea().eh().toString()));
        if (!f.a(arrby, new e(arrby2))) return "ok";
        oe.LOGGER.info("forced shut down");
        System.exit(-1);
        return "ok";
    }

    @RequestMapping(value={"/debug/block-info/get"}, method={RequestMethod.GET})
    @th
    public BlockDebugInfo t(@RequestParam(value="height") String string) {
        BlockHeight blockHeight = new BlockHeight(Long.parseLong(string));
        AccountAnalyzer accountAnalyzer = this.dB.cd();
        org.nem.nis.dbmodel.Block block = this.cV.s(blockHeight);
        Block block2 = lt.a(block, accountAnalyzer);
        org.nem.nis.dbmodel.Block block3 = 1 == blockHeight.bw() ? null : this.cV.s(blockHeight.by());
        Block block4 = null == block3 ? null : lt.a(block3, accountAnalyzer);
        gu gu = new gu(accountAnalyzer);
        gu.cn();
        BigInteger bigInteger = gu.i(block2);
        BigInteger bigInteger2 = null == block4 ? BigInteger.ZERO : gu.c(block4, block2);
        int n = null == block4 ? 0 : block2.bf().b(block4.bf());
        BlockDebugInfo blockDebugInfo = new BlockDebugInfo(block2.ao(), block2.bf(), block2.be().ai(), block2.aB(), bigInteger, bigInteger2, n);
        for (Transaction transaction : block2.aA()) {
            blockDebugInfo.a(oe.m(transaction));
        }
        return blockDebugInfo;
    }

    @RequestMapping(value={"/debug/timers"}, method={RequestMethod.GET})
    @th
    public SerializableList<NisAsyncTimerVisitor> fe() {
        return new SerializableList<NisAsyncTimerVisitor>((Collection<NisAsyncTimerVisitor>)this.ec.eU());
    }

    @RequestMapping(value={"/debug/connections/incoming"}, method={RequestMethod.GET})
    @th
    public AuditCollection ff() {
        return this.ec.eT();
    }

    @RequestMapping(value={"/debug/connections/outgoing"}, method={RequestMethod.GET})
    @th
    public AuditCollection fg() {
        return this.ec.eS();
    }

    private static TransactionDebugInfo m(Transaction transaction) {
        Message message;
        Address address = Address.e("N/A");
        Amount amount = Amount.cs;
        String string = "";
        if (!(transaction instanceof TransferTransaction)) return new TransactionDebugInfo(transaction.bf(), transaction.aV(), transaction.be().ai(), address, amount, transaction.aU(), string);
        TransferTransaction transferTransaction = (TransferTransaction)transaction;
        address = transferTransaction.ba().ai();
        amount = transferTransaction.av();
        if (null == (message = transferTransaction.bb()) || !message.R()) return new TransactionDebugInfo(transaction.bf(), transaction.aV(), transaction.be().ai(), address, amount, transaction.aU(), string);
        string = h.h(message.T());
        return new TransactionDebugInfo(transaction.bf(), transaction.aV(), transaction.be().ai(), address, amount, transaction.aU(), string);
    }
}
