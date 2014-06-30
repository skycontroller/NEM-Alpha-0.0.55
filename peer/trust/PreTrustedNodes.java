package org.nem.peer.trust;

import java.util.Set;
import org.nem.core.math.a;
import org.nem.peer.node.Node;

public class PreTrustedNodes {
    private Set<Node> gZ;

    public PreTrustedNodes(Set<Node> set) {
        this.gZ = set;
    }

    public int getSize() {
        return this.gZ.size();
    }

    public Set<Node> eu() {
        return this.gZ;
    }

    public boolean j(Node node) {
        return this.gZ.contains(node);
    }

    public a b(Node[] arrnode) {
        int n = this.getSize();
        a a = new a(arrnode.length);
        if (0 == n) {
            a.c(1.0 / (double)arrnode.length);
            return a;
        }
        for (int i = 0; i < arrnode.length; ++i) {
            a.a(i, this.j(arrnode[i]) ? 1.0 / (double)n : 0.0);
        }
        a.normalize();
        return a;
    }
}