package org.nem.nis.a;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.SerializableEntity;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.a.g.jf;
import org.nem.nis.a.g.th;
import org.nem.nis.controller.viewmodels.AuthenticatedBlockHeightRequest;
import org.nem.nis.d.tu;
import org.nem.peer.PeerNetwork;
import org.nem.peer.node.AuthenticatedResponse;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeChallenge;
import org.nem.peer.node.NodeIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class go {
    private final tu eb;
    private final NisPeerNetworkHost ec;

    @Autowired(required=1)
    go(tu tu, NisPeerNetworkHost nisPeerNetworkHost) {
        this.eb = tu;
        this.ec = nisPeerNetworkHost;
    }

    @RequestMapping(value={"/block/get"}, method={RequestMethod.GET})
    @jf
    @th
    public Block r(@RequestParam(value="blockHash") String string) {
        Hash hash = Hash.a(string);
        return this.eb.g(hash);
    }

    @RequestMapping(value={"/block/at/public"}, method={RequestMethod.POST})
    @th
    public Block q(@RequestBody BlockHeight blockHeight) {
        return this.eb.u(blockHeight);
    }

    @RequestMapping(value={"/block/at"}, method={RequestMethod.POST})
    @jf
    public AuthenticatedResponse<Block> a(@RequestBody AuthenticatedBlockHeightRequest authenticatedBlockHeightRequest) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<Block>(this.q((BlockHeight)authenticatedBlockHeightRequest.dV()), node.dW(), authenticatedBlockHeightRequest.dX());
    }
}