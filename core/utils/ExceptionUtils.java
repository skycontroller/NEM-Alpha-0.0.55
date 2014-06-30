package org.nem.core.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class ExceptionUtils {
    private ExceptionUtils() {
    }

    public static void a(CheckedRunnable checkedRunnable) {
        ExceptionUtils.a(checkedRunnable, exception -> new RuntimeException(exception));
    }

    public static <E extends RuntimeException> void a(CheckedRunnable checkedRunnable, Function<Exception, E> function) {
        ExceptionUtils.a(new a(checkedRunnable), function);
    }

    public static <T> T a(Callable<T> callable) {
        return ExceptionUtils.a(callable, exception -> new RuntimeException(exception));
    }

    public static <T, E extends RuntimeException> T a(Callable<T> callable, Function<Exception, E> function) {
        try {
            return callable.call();
        }
        catch (ExecutionException var2_2) {
            if (!RuntimeException.class.isAssignableFrom(var2_2.getCause().getClass())) throw (RuntimeException)function.apply(var2_2);
            throw (RuntimeException)var2_2.getCause();
        }
        catch (RuntimeException var2_3) {
            throw var2_3;
        }
        catch (InterruptedException var2_4) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(var2_4);
        }
        catch (Exception var2_5) {
            throw (RuntimeException)function.apply(var2_5);
        }
    }

    static class a
    implements Callable<Void> {
        private final CheckedRunnable cQ;

        public a(CheckedRunnable checkedRunnable) {
            this.cQ = checkedRunnable;
        }

        @Override
        public Void call() throws Exception {
            this.cQ.call();
            return null;
        }
    }

    public interface CheckedRunnable {
        public void call() throws Exception;
    }

}