package org.nem.nis;

import java.io.PrintStream;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.g;
import org.nem.core.model.h;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.s;
import org.nem.core.serialization.b;
import org.nem.core.serialization.d;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;
import org.nem.deploy.CommonStarter;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.BlockChain;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.a;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.gu;
import org.nem.nis.j.nn;
import org.nem.nis.j.zh;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.y.lt;
import org.nem.nis.y.vm;
import org.springframework.beans.factory.annotation.Autowired;

public class gn {
    private static final Logger LOGGER = Logger.getLogger(gn.class.getName());
    public static final org.nem.core.time.b o = CommonStarter.o;
    private Block hB;
    private Hash hC;
    @Autowired
    private nn cU;
    @Autowired
    private zh cV;
    @Autowired
    private AccountAnalyzer aO;
    @Autowired
    private BlockChain dB;
    @Autowired
    private NisPeerNetworkHost dC;
    @Autowired
    private BlockChainLastBlockLayer cW;

    private void cp() {
        gn.LOGGER.info("starting analysis...");
        org.nem.nis.dbmodel.Block block = this.cV.f(this.hC);
        gn.LOGGER.info(String.format("hex: %s", block.getGenerationHash()));
        if (!block.getGenerationHash().equals(Hash.a("c5d54f3ed495daec32b4cbba7a44555f9ba83ea068e5f1923e9edb774d207cd8"))) {
            gn.LOGGER.severe("couldn't find nemesis block, you're probably using developer's build, drop the db and rerun");
            System.exit(-1);
        }
        Block block2 = null;
        a a = new a(this.aO);
        do {
            Block block3;
            Long l;
            if ((block3 = lt.a(block, this.aO.ca())).ao().bw() % 1000 == 0) {
                System.out.print(String.format("\r%d", block3.ao().bw()));
            }
            if (null != block2) {
                this.dB.a(block2, block3);
            }
            block3.a(a);
            block3.execute();
            block3.b(a);
            if (null == block2) {
                for (Account account : this.aO) {
                    if (NemesisBlock.bm.equals(account.ai())) continue;
                    account.as().bp();
                }
            }
            block2 = block3;
            if (null == (l = block.getNextBlockId())) {
                System.out.println();
                System.out.flush();
                this.cW.e(block);
                break;
            }
            if ((block = this.cV.f(l.longValue())) != null || this.cW.dn() != null) continue;
            gn.LOGGER.severe("inconsistent db state, you're probably using developer's build, drop the db and rerun");
            System.exit(-1);
        } while (block != null);
        this.x(block2.ao());
    }

    private void x(BlockHeight blockHeight) {
        gn.LOGGER.info("Known accounts: " + this.aO.size());
        gn.LOGGER.info(String.format("Initializing PoI for (%d) accounts", this.aO.size()));
        gu gu = new gu(this.aO);
        BlockHeight blockHeight2 = gu.w(blockHeight);
        this.aO.o(blockHeight2);
        gn.LOGGER.info("PoI initialized");
    }

    @PostConstruct
    private void init() {
        gn.LOGGER.warning("context ================== current: " + gn.o.ac());
        this.hB = this.eR();
        this.hC = h.a(this.hB);
        this.cq();
        this.cr();
        this.cp();
    }

    private NemesisBlock eR() {
        Account account = this.aO.e(NemesisBlock.bm);
        account.a(NemesisBlock.bn);
        account.as().h(BlockHeight.cy, NemesisBlock.bn);
        account.a(BlockHeight.cy);
        return NemesisBlock.a(new org.nem.core.serialization.d(this.aO.ca()));
    }

    private void cq() {
        gn.LOGGER.info("nemesis block hash:" + this.hC);
        d d = this.hB.be().ah();
        Address address = this.hB.be().ai();
        gn.LOGGER.info("nemesis account private key          : " + d.q());
        gn.LOGGER.info("nemesis account            public key: " + d.getPublicKey());
        gn.LOGGER.info("nemesis account compressed public key: " + address.ax());
        gn.LOGGER.info("nemesis account generation hash      : " + this.hB.getGenerationHash());
    }

    private void cr() {
        if (0 != this.cV.cV()) {
            return;
        }
        this.l(this.hB);
    }

    private org.nem.nis.dbmodel.Block l(Block block) {
        org.nem.nis.dbmodel.Block block2 = this.cV.f(this.hC);
        if (null != block2) {
            return block2;
        }
        block2 = lt.a(block, new vm(this.cU));
        this.cV.a(block2);
        return block2;
    }
}
