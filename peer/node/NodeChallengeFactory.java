package org.nem.peer.node;

import java.security.SecureRandom;
import org.nem.peer.node.NodeChallenge;

public class NodeChallengeFactory {
    private static final int gy = 64;
    private final SecureRandom secureRandom = new SecureRandom();

    public NodeChallenge ed() {
        byte[] arrby = new byte[64];
        this.secureRandom.nextBytes(arrby);
        return new NodeChallenge(arrby);
    }
}