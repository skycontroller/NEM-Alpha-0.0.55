package org.nem.core.math;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.DoubleFunction;
import java.util.function.Supplier;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.nem.core.math.DenseMatrix;
import org.nem.core.math.Matrix;
import org.nem.core.utils.f;

public class a {
    private final int size;
    private final double[] M;
    private final DenseMatrix N;

    public a(int n) {
        if (0 == n) {
            throw new IllegalArgumentException("cannot create a vector of zero size");
        }
        this.size = n;
        this.M = new double[this.size];
        this.N = new DenseMatrix(this.size, 1, this.M);
    }

    public /* varargs */ a(double ... arrd) {
        if (null == arrd || 0 == arrd.length) {
            throw new IllegalArgumentException("vector must not be null and have a non-zero size");
        }
        this.size = arrd.length;
        this.M = arrd;
        this.N = new DenseMatrix(this.size, 1, this.M);
    }

    private a(Matrix matrix) {
        this.N = (DenseMatrix)matrix;
        this.M = this.N.D();
        this.size = this.M.length;
    }

    public int size() {
        return this.N.getRowCount();
    }

    public double d(int n) {
        return this.N.a(n, 0);
    }

    public void a(int n, double d) {
        this.N.a(n, 0, d);
    }

    public void b(int n, double d) {
        this.N.b(n, 0, d);
    }

    public void normalize() {
        this.N.L();
    }

    public void a(double d) {
        this.N.a(d);
    }

    public a a(a a) {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.a((Matrix)a.N);
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    public a b(a a) {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.b((Matrix)a.N);
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    public double z() {
        return this.N.z();
    }

    public double sum() {
        return this.N.sum();
    }

    public a e(int n) {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.f(n);
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    public a b(double d) {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.e(d);
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    public a A() {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.P();
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    public a B() {
        return this.a(new Supplier<Matrix>(){

            public Matrix I() {
                return a.this.N.O();
            }

            @Override
            public /* synthetic */ Object get() {
                return this.I();
            }
        });
    }

    private a a(Supplier<Matrix> supplier) {
        Matrix matrix = supplier.get();
        return new a(matrix);
    }

    public final boolean C() {
        return this.N.Q();
    }

    public double[] D() {
        return this.M;
    }

    public void c(double d) {
        for (int i = 0; i < this.M.length; ++i) {
            this.M[i] = d;
        }
    }

    public boolean E() {
        if (0.0 == this.M[0]) {
            return false;
        }
        this.a(this.M[0]);
        return true;
    }

    public double F() {
        double d = this.M[0];
        for (double d2 : this.M) {
            d = Math.max(d, d2);
        }
        return d;
    }

    public double G() {
        Median median = new Median();
        return median.evaluate(this.M);
    }

    public double H() {
        a a = new a(this.size);
        return this.d(a);
    }

    public double c(a a) {
        return this.a(a, Math::abs);
    }

    public double d(a a) {
        double d = this.a(a, d -> d * d);
        return Math.sqrt(d);
    }

    private double a(a a, DoubleFunction<Double> doubleFunction) {
        if (this.size != a.size) {
            throw new IllegalArgumentException("cannot determine the distance between vectors with different sizes");
        }
        double d = 0.0;
        for (int i = 0; i < this.size; ++i) {
            double d2 = this.M[i] - a.M[i];
            d+=doubleFunction.apply(d2).doubleValue();
        }
        return d;
    }

    public String toString() {
        DecimalFormat decimalFormat = f.bK();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.size; ++i) {
            if (0 != i) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(decimalFormat.format(this.M[i]));
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(this.M);
    }

    public boolean equals(Object object) {
        if (!(object instanceof a)) {
            return false;
        }
        a a = (a)object;
        return Arrays.equals(this.M, a.M);
    }

}