package org.nem.nis.a;

import java.util.logging.Logger;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Account;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializableList;
import org.nem.core.serialization.b;
import org.nem.nis.BlockChain;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.a.g.bd;
import org.nem.nis.a.g.jf;
import org.nem.nis.a.g.jv;
import org.nem.nis.a.g.th;
import org.nem.nis.controller.viewmodels.AuthenticatedBlockHeightRequest;
import org.nem.nis.d.dl;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.y.lt;
import org.nem.peer.PeerNetwork;
import org.nem.peer.node.AuthenticatedResponse;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeChallenge;
import org.nem.peer.node.NodeIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ty {
    private static final Logger LOGGER = Logger.getLogger(ty.class.getName());
    private final b cG;
    private final dl ed;
    private final BlockChainLastBlockLayer cW;
    private final BlockChain dB;
    private final NisPeerNetworkHost ec;

    @Autowired(required=1)
    public ty(dl dl, b b, BlockChainLastBlockLayer blockChainLastBlockLayer, BlockChain blockChain, NisPeerNetworkHost nisPeerNetworkHost) {
        this.ed = dl;
        this.cG = b;
        this.cW = blockChainLastBlockLayer;
        this.dB = blockChain;
        this.ec = nisPeerNetworkHost;
    }

    @RequestMapping(value={"/chain/last-block"}, method={RequestMethod.GET})
    @th
    public Block cD() {
        Block block = lt.a(this.cW.dn(), this.cG);
        ty.LOGGER.info("/chain/last-block height:" + block.ao() + " signer:" + block.be());
        return block;
    }

    @RequestMapping(value={"/chain/last-block"}, method={RequestMethod.POST})
    @jf
    @bd
    public AuthenticatedResponse<Block> a(@RequestBody NodeChallenge nodeChallenge) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<Block>(this.cD(), node.dW(), nodeChallenge);
    }

    @RequestMapping(value={"/chain/local-blocks-after"}, method={RequestMethod.POST})
    @jv
    public SerializableList<Block> y(@RequestBody BlockHeight blockHeight) {
        org.nem.nis.dbmodel.Block block = this.ed.s(blockHeight);
        SerializableList<Block> serializableList = new SerializableList<Block>(1440);
        for (int i = 0; i < 1440; ++i) {
            Long l;
            if (null == (l = block.getNextBlockId())) return serializableList;
            block = this.ed.f(l.longValue());
            serializableList.f(lt.a(block, this.cG));
        }
        return serializableList;
    }

    @RequestMapping(value={"/chain/blocks-after"}, method={RequestMethod.POST})
    @jf
    @bd
    public AuthenticatedResponse<SerializableList<Block>> b(@RequestBody AuthenticatedBlockHeightRequest authenticatedBlockHeightRequest) {
        org.nem.nis.dbmodel.Block block = this.ed.s((BlockHeight)authenticatedBlockHeightRequest.dV());
        SerializableList<Block> serializableList = new SerializableList<Block>(1440);
        for (int i = 0; i < 1440; ++i) {
            Long l;
            if (null == (l = block.getNextBlockId())) break;
            block = this.ed.f(l.longValue());
            serializableList.f(lt.a(block, this.cG));
        }
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse(serializableList, node.dW(), authenticatedBlockHeightRequest.dX());
    }

    @RequestMapping(value={"/chain/hashes-from"}, method={RequestMethod.POST})
    @jf
    @bd
    public AuthenticatedResponse<HashChain> c(@RequestBody AuthenticatedBlockHeightRequest authenticatedBlockHeightRequest) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<HashChain>(this.ed.c((BlockHeight)authenticatedBlockHeightRequest.dV(), 1440), node.dW(), authenticatedBlockHeightRequest.dX());
    }

    @RequestMapping(value={"/chain/score"}, method={RequestMethod.GET})
    @th
    public BlockChainScore cE() {
        return this.dB.cc();
    }

    @RequestMapping(value={"/chain/score"}, method={RequestMethod.POST})
    @jf
    @th
    @bd
    public AuthenticatedResponse<BlockChainScore> b(@RequestBody NodeChallenge nodeChallenge) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<BlockChainScore>(this.cE(), node.dW(), nodeChallenge);
    }
}
