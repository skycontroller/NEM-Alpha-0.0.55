package org.nem.core.async;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class f {
    private static final ScheduledExecutorService k = Executors.newSingleThreadScheduledExecutor();

    public static <T> CompletableFuture<T> s(int n) {
        CompletableFuture completableFuture = new CompletableFuture();
        f.k.schedule(() -> completableFuture.complete(null), (long)n, TimeUnit.MILLISECONDS);
        return completableFuture;
    }
}