package org.nem.core.math;

import java.text.DecimalFormat;
import java.util.function.DoubleConsumer;
import org.nem.core.math.Matrix;
import org.nem.core.utils.f;

public class b
extends Matrix {
    private static final double ab = 1.6;
    private final int numRows;
    private final int ac;
    private double[][] ad = null;
    private int[][] ae = null;
    private int[] af = null;

    public b(int n, int n2, int n3) {
        super(n, n2);
        this.numRows = n;
        this.ac = n3;
        this.ad = new double[n][];
        this.ae = new int[n][];
        this.af = new int[n];
        for (int i = 0; i < n; ++i) {
            this.ad[i] = new double[n3];
            this.ae[i] = new int[n3];
            this.af[i] = 0;
        }
    }

    @Override
    protected final Matrix create(int n, int n2) {
        return new b(n, n2, this.ac);
    }

    @Override
    protected final double getAtUnchecked(int n, int n2) {
        for (int i = 0; i < this.af[n]; ++i) {
            if (this.ae[n][i] != n2) continue;
            return this.ad[n][i];
        }
        return 0.0;
    }

    @Override
    protected final void setAtUnchecked(int n, int n2, double d) {
        if (d == 0.0) {
            for (int i = 0; i < this.af[n]; ++i) {
                if (this.ae[n][i] != n2) continue;
                this.remove(n, i);
                return;
            }
        } else {
            int n3 = this.ae[n].length;
            for (int i = 0; i < this.af[n]; ++i) {
                if (this.ae[n][i] != n2) continue;
                this.ad[n][i] = d;
                return;
            }
            if (this.af[n] == n3) {
                this.reallocate(n);
            }
            this.ae[n][this.af[n]] = n2;
            this.ad[n][this.af[n]] = d;
            int[] arrn = this.af;
            int n4 = n;
            n4[arrn] = n4[arrn] + true;
        }
    }

    @Override
    protected final void forEach(Matrix.ElementVisitorFunction elementVisitorFunction) {
        for (int i = 0; i < this.numRows; ++i) {
            double[] arrd = this.ad[i];
            int[] arrn = this.ae[i];
            int n = this.af[i];
            int n2 = 0;
            while (n2 < n) {
                int n3 = n2++;
                elementVisitorFunction.a(i, arrn[n2], arrd[n2], d -> {
                    arrd[n] = d;
                }
                );
            }
        }
    }

    @Override
    protected final void forEach(Matrix.ReadOnlyElementVisitorFunction readOnlyElementVisitorFunction) {
        for (int i = 0; i < this.numRows; ++i) {
            double[] arrd = this.ad[i];
            int[] arrn = this.ae[i];
            int n = this.af[i];
            for (int j = 0; j < n; ++j) {
                readOnlyElementVisitorFunction.visit(i, arrn[j], arrd[j]);
            }
        }
    }

    public int g(int n) {
        return this.af[n];
    }

    public int h(int n) {
        return this.ae[n].length;
    }

    private void remove(int n, int n2) {
        int n3 = this.af[n] - 1;
        if (n3 > 0) {
            this.ae[n][n2] = this.ae[n][n3];
            this.ad[n][n2] = this.ad[n][n3];
        }
        int[] arrn = this.af;
        int n4 = n;
        n4[arrn] = n4[arrn] - true;
    }

    private void reallocate(int n) {
        int n2 = this.ae[n].length;
        int n3 = (int)Math.ceil(1.6 * (double)n2);
        int[] arrn = new int[n3];
        double[] arrd = new double[n3];
        System.arraycopy(this.ae[n], 0, arrn, 0, n2);
        System.arraycopy(this.ad[n], 0, arrd, 0, n2);
        this.ae[n] = arrn;
        this.ad[n] = arrd;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("[%d x %d]", this.getRowCount(), this.getColumnCount()));
        this.forEach((n, n2, d) -> {
            stringBuilder.append(b.c(n, n2, d));
        }
        );
        return stringBuilder.toString();
    }

    private static String c(int n, int n2, double d) {
        DecimalFormat decimalFormat = f.bK();
        return String.format("%s(%d, %d) -> %s", System.lineSeparator(), n, n2, decimalFormat.format(d));
    }
}