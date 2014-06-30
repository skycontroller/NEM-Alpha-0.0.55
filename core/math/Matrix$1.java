package org.nem.core.math;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;
import org.nem.core.math.a;

public abstract class Matrix {
    private final int numRows;
    private final int S;

    protected Matrix(int n, int n2) {
        this.numRows = n;
        this.S = n2;
    }

    public final int getElementCount() {
        return this.numRows * this.S;
    }

    public final int getRowCount() {
        return this.numRows;
    }

    public final int getColumnCount() {
        return this.S;
    }

    public final double a(int n, int n2) {
        this.b(n, n2);
        return this.getAtUnchecked(n, n2);
    }

    public final void a(int n, int n2, double d) {
        this.b(n, n2);
        this.setAtUnchecked(n, n2, d);
    }

    public final void b(int n, int n2, double d) {
        double d2 = this.a(n, n2);
        this.setAtUnchecked(n, n2, d2 + d);
    }

    public final a J() {
        double[] arrd = new double[this.numRows];
        this.forEach((n, n2, d) -> {
            double[] arrd2 = arrd;
            int n3 = n;
            n3[arrd2] = n3[arrd2] + d;
        }
        );
        return new a(arrd);
    }

    public final a K() {
        return new a(this.a(d -> d));
    }

    private double[] a(DoubleUnaryOperator doubleUnaryOperator) {
        double[] arrd = new double[this.S];
        this.forEach((n, n2, d) -> {
            double[] arrd2 = arrd;
            int n3 = n2;
            n3[arrd2] = n3[arrd2] + doubleUnaryOperator.applyAsDouble(d);
        }
        );
        return arrd;
    }

    public void L() {
        double[] arrd = this.a(Math::abs);
        this.forEach(new ElementVisitorFunction(){

            @Override
            public void a(int n, int n2, double d, DoubleConsumer doubleConsumer) {
                double d2 = arrd[n2];
                if (0.0 == d2) {
                    return;
                }
                doubleConsumer.accept(d / d2);
            }
        });
    }

    public void M() {
        this.forEach(new ElementVisitorFunction(){

            @Override
            public void a(int n2, int n2, double d, DoubleConsumer doubleConsumer) {
                if (d >= 0.0) return;
                doubleConsumer.accept(0.0);
            }
        });
    }

    public final void a(double d) {
        this.forEach(new ElementVisitorFunction(){

            @Override
            public void a(int n2, int n2, double d, DoubleConsumer doubleConsumer) {
                doubleConsumer.accept(d / d);
            }
        });
    }

    public Matrix a(Matrix matrix) {
        return this.a(matrix, false, (d, d2) -> d * d2);
    }

    public Matrix b(Matrix matrix) {
        return this.a(matrix, true, (d, d2) -> d + d2);
    }

    private Matrix a(Matrix matrix, boolean bl, DoubleBinaryOperator doubleBinaryOperator) {
        if (!this.c(matrix)) {
            throw new IllegalArgumentException("matrix sizes must be equal");
        }
        Matrix matrix2 = this.create(this.getRowCount(), this.getColumnCount());
        this.forEach((n, n2, d) -> {
            matrix2.setAtUnchecked(n, n2, doubleBinaryOperator.applyAsDouble(d, matrix.getAtUnchecked(n, n2)));
        }
        );
        if (!bl) return matrix2;
        matrix.forEach((n, n2, d) -> {
            matrix2.setAtUnchecked(n, n2, doubleBinaryOperator.applyAsDouble(d, this.getAtUnchecked(n, n2)));
        }
        );
        return matrix2;
    }

    public final double z() {
        return this.b(Math::abs);
    }

    public final double sum() {
        return this.b(d -> d);
    }

    private double b(DoubleUnaryOperator doubleUnaryOperator) {
        double[] arrd = new double[]{0.0};
        this.forEach((n2, n2, d) -> {
            double[] arrd2 = arrd;
            false[arrd2] = false[arrd2] + doubleUnaryOperator.applyAsDouble(d);
        }
        );
        return arrd[0];
    }

    public a f(a a) {
        if (this.S != a.size()) {
            throw new IllegalArgumentException("vector size and matrix column count must be equal");
        }
        double[] arrd = new double[this.numRows];
        double[] arrd2 = a.D();
        this.forEach((n, n2, d) -> {
            double[] arrd3 = arrd;
            int n3 = n;
            n3[arrd3] = n3[arrd3] + d * arrd2[n2];
        }
        );
        return new a(arrd);
    }

    public final Matrix N() {
        Matrix matrix = this.create(this.getColumnCount(), this.getRowCount());
        this.forEach((n, n2, d) -> {
            matrix.setAtUnchecked(n2, n, d);
        }
        );
        return matrix;
    }

    public Matrix f(int n) {
        double d = Math.pow(10.0, n);
        return this.c(d2 -> (double)Math.round(d2 * d) / d);
    }

    public Matrix e(double d) {
        return this.c(d2 -> d2 * d);
    }

    public Matrix O() {
        return this.c(Math::abs);
    }

    public Matrix P() {
        return this.c(Math::sqrt);
    }

    private Matrix c(DoubleUnaryOperator doubleUnaryOperator) {
        Matrix matrix = this.create(this.getRowCount(), this.getColumnCount());
        this.forEach((n, n2, d) -> {
            matrix.setAtUnchecked(n, n2, doubleUnaryOperator.applyAsDouble(d));
        }
        );
        return matrix;
    }

    public final boolean c(Matrix matrix) {
        return this.numRows == matrix.numRows && this.S == matrix.S;
    }

    private void b(int n, int n2) {
        if (n < 0 || n >= this.numRows) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
        if (n2 >= 0 && n2 < this.S) return;
        throw new IndexOutOfBoundsException("Column index out of bounds");
    }

    public final boolean Q() {
        return 0.0 == this.z();
    }

    public int hashCode() {
        return this.getRowCount() ^ this.getColumnCount();
    }

    public boolean equals(Object object) {
        Matrix matrix;
        Matrix matrix2;
        if (!(object instanceof Matrix)) {
            return false;
        }
        if (!this.c(matrix = (Matrix)object)) {
            return false;
        }
        return 0.0 == (matrix2 = this.a(matrix, true, (d, d2) -> d == d2 ? 0.0 : 1.0)).sum();
    }

    protected void forEach(ReadOnlyElementVisitorFunction readOnlyElementVisitorFunction) {
        this.forEach(new ElementVisitorFunction(){

            @Override
            public void a(int n, int n2, double d, DoubleConsumer doubleConsumer) {
                readOnlyElementVisitorFunction.visit(n, n2, d);
            }
        });
    }

    protected abstract Matrix create(int var1, int var2);

    protected abstract double getAtUnchecked(int var1, int var2);

    protected abstract void setAtUnchecked(int var1, int var2, double var3);

    protected abstract void forEach(ElementVisitorFunction var1);

    @FunctionalInterface
    public interface ElementVisitorFunction {
        public void a(int var1, int var2, double var3, DoubleConsumer var5);
    }

    @FunctionalInterface
    public interface ReadOnlyElementVisitorFunction {
        public void visit(int var1, int var2, double var3);
    }

}
