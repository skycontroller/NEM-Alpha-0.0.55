package org.nem.peer.trust;

import java.util.Collection;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeCollection;

public class TrustUtils {
    public static Node[] a(NodeCollection nodeCollection, Node node) {
        int n = nodeCollection.ee().size() + nodeCollection.ef().size() + 1;
        Node[] arrnode = new Node[n];
        int n2 = 0;
        for (Node node22 : nodeCollection.ee()) {
            arrnode[n2++] = node22;
        }
        for (Node node22 : nodeCollection.ef()) {
            arrnode[n2++] = node22;
        }
        arrnode[n2] = node;
        return arrnode;
    }
}