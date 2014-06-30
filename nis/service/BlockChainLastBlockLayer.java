package org.nem.nis.service;

import java.util.logging.Logger;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.BlockChain;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.j.nn;
import org.nem.nis.j.zh;
import org.nem.nis.y.lt;
import org.nem.nis.y.vm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockChainLastBlockLayer {
    private static final Logger LOGGER = Logger.getLogger(BlockChain.class.getName());
    private final nn cU;
    private final zh cV;
    private org.nem.nis.dbmodel.Block fa;

    @Autowired(required=1)
    public BlockChainLastBlockLayer(nn nn, zh zh) {
        this.cU = nn;
        this.cV = zh;
    }

    public org.nem.nis.dbmodel.Block dn() {
        return this.fa;
    }

    public Long do() {
        return this.fa.getHeight();
    }

    public void e(org.nem.nis.dbmodel.Block block) {
        BlockChainLastBlockLayer.LOGGER.info("analyzing last block: " + Long.toString(block.getShortId()));
        this.fa = block;
    }

    public boolean n(Block block) {
        org.nem.nis.dbmodel.Block block2 = lt.a(block, new vm(this.cU));
        this.cV.a(block2);
        this.fa.setNextBlockId(block2.getId());
        this.cV.b(this.fa);
        this.fa = block2;
        return true;
    }

    public void t(BlockHeight blockHeight) {
        this.cV.r(blockHeight);
        this.fa = this.cV.s(blockHeight);
    }
}