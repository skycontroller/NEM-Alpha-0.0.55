package org.nem.core.math;

import java.util.function.DoubleConsumer;
import org.nem.core.math.Matrix;

@FunctionalInterface
public interface Matrix$ElementVisitorFunction {
    public void a(int var1, int var2, double var3, DoubleConsumer var5);
}