package org.nem.core.math;

import java.text.DecimalFormat;
import java.util.function.DoubleConsumer;
import org.nem.core.math.Matrix;
import org.nem.core.utils.f;

public final class DenseMatrix
extends Matrix {
    final int S;
    final double[] T;

    public DenseMatrix(int n, int n2) {
        super(n, n2);
        this.S = n2;
        this.T = new double[this.getElementCount()];
    }

    public DenseMatrix(int n, int n2, double[] arrd) {
        super(n, n2);
        if (arrd.length != this.getElementCount()) {
            throw new IllegalArgumentException("incompatible number of values");
        }
        this.S = n2;
        this.T = arrd;
    }

    public double[] D() {
        return this.T;
    }

    @Override
    protected final Matrix create(int n, int n2) {
        return new DenseMatrix(n, n2);
    }

    @Override
    protected final double getAtUnchecked(int n, int n2) {
        return this.T[n * this.S + n2];
    }

    @Override
    protected final void setAtUnchecked(int n, int n2, double d) {
        this.T[n * this.S + n2] = d;
    }

    @Override
    protected final void forEach(Matrix.ElementVisitorFunction elementVisitorFunction) {
        for (int i = 0; i < this.getRowCount(); ++i) {
            int n = 0;
            while (n < this.getColumnCount()) {
                int n2 = i;
                int n3 = n++;
                a a = new a(n2, n3);
                elementVisitorFunction.a(i, n, this.getAtUnchecked(i, n), a);
            }
        }
    }

    public String toString() {
        DecimalFormat decimalFormat = f.bK();
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach((n, n2, d) -> {
            if (0 != n && 0 == n2) {
                stringBuilder.append(System.lineSeparator());
            }
            if (0 != n2) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(decimalFormat.format(d));
        }
        );
        return stringBuilder.toString();
    }

    class a
    implements DoubleConsumer {
        final int U;
        final int V;

        a(int n, int n2) {
            this.U = n;
            this.V = n2;
        }

        @Override
        public void accept(double d) {
            DenseMatrix.this.setAtUnchecked(this.U, this.V, d);
        }
    }

}