package org.nem.peer.trust.score;

import org.nem.core.math.DenseMatrix;
import org.nem.core.math.Matrix;
import org.nem.core.math.a;
import org.nem.core.utils.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.RealDouble;
import org.nem.peer.trust.score.Score;

public abstract class Scores<T extends Score> {
    private final org.nem.core.utils.a<Node, T> hk;

    public Scores() {
        this.hk = new org.nem.core.utils.a<Node, T>(){

            protected T eH() {
                return Scores.this.ez();
            }

            @Override
            protected /* synthetic */ Object bJ() {
                return this.eH();
            }
        };
    }

    protected abstract T ez();

    public T d(Node node, Node node2) {
        return (Score)this.hk.a(node, node2);
    }

    public a b(Node node, Node[] arrnode) {
        a a = new a(arrnode.length);
        for (int i = 0; i < arrnode.length; ++i) {
            T T = this.d(node, arrnode[i]);
            a.a(i, T.eG().get());
        }
        return a;
    }

    public void a(Node node, Node[] arrnode, a a) {
        if (arrnode.length != a.size()) {
            throw new IllegalArgumentException("nodes and scoreVector must be same size");
        }
        for (int i = 0; i < arrnode.length; ++i) {
            T T = this.d(node, arrnode[i]);
            T.eG().set(a.d(i));
        }
    }

    public Matrix c(Node[] arrnode) {
        int n = arrnode.length;
        DenseMatrix denseMatrix = new DenseMatrix(n, n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                T T = this.d(arrnode[i], arrnode[j]);
                denseMatrix.a(j, i, T.eG().get());
            }
        }
        return denseMatrix;
    }

    public void d(Node[] arrnode) {
        for (Node node : arrnode) {
            a a = this.b(node, arrnode);
            a.normalize();
            this.a(node, arrnode, a);
        }
    }

}