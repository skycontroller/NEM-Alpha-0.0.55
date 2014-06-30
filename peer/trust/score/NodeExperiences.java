package org.nem.peer.trust.score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.math.DenseMatrix;
import org.nem.core.math.Matrix;
import org.nem.core.utils.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiencePair;

public class NodeExperiences {
    private final a<Node, NodeExperience> hg;

    public NodeExperiences() {
        this.hg = new a<Node, NodeExperience>(){

            protected NodeExperience eE() {
                return new NodeExperience();
            }

            @Override
            protected /* synthetic */ Object bJ() {
                return this.eE();
            }
        };
    }

    public NodeExperience c(Node node, Node node2) {
        return this.hg.a(node, node2);
    }

    private Map<Node, NodeExperience> k(Node node) {
        return this.hg.e(node);
    }

    public Matrix a(Node node, Node[] arrnode) {
        DenseMatrix denseMatrix = new DenseMatrix(arrnode.length, arrnode.length);
        for (int i = 0; i < arrnode.length; ++i) {
            if (node.equals(arrnode[i])) continue;
            for (int j = 0; j < arrnode.length; ++j) {
                if (arrnode[i].equals(arrnode[j])) continue;
                NodeExperience nodeExperience = this.c(node, arrnode[j]);
                NodeExperience nodeExperience2 = this.c(arrnode[i], arrnode[j]);
                if (nodeExperience.eC() <= 0 || nodeExperience2.eC() <= 0) continue;
                denseMatrix.a(i, j, 1.0);
            }
        }
        return denseMatrix;
    }

    public List<NodeExperiencePair> l(Node node) {
        ArrayList arrayList = new ArrayList();
        Map<Node, NodeExperience> map = this.k(node);
        arrayList.addAll(map.entrySet().stream().map(entry -> new NodeExperiencePair((Node)entry.getKey(), (NodeExperience)entry.getValue())).collect(Collectors.toList()));
        return arrayList;
    }

    public void a(Node node, List<NodeExperiencePair> list) {
        Map<Node, NodeExperience> map = this.k(node);
        for (NodeExperiencePair nodeExperiencePair : list) {
            map.put(nodeExperiencePair.dY(), nodeExperiencePair.eD());
        }
    }

}