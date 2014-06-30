package org.nem.core.async;

import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.nem.core.async.a;
import org.nem.core.async.e;
import org.nem.core.async.f;

public class AsyncTimer
implements Closeable {
    private final Supplier<CompletableFuture<?>> e;
    private final org.nem.core.async.a f;
    private final CompletableFuture<?> g;
    private final e j;
    private final AtomicBoolean h = new AtomicBoolean();
    private final CompletableFuture<?> i = new CompletableFuture();

    public AsyncTimer(Supplier<CompletableFuture<?>> supplier, int n, org.nem.core.async.a a, e e) {
        this.e = supplier;
        this.f = a;
        this.j = AsyncTimer.a(e);
        this.g = this.b(n);
    }

    private AsyncTimer(CompletableFuture<?> completableFuture, Supplier<CompletableFuture<?>> supplier, org.nem.core.async.a a, e e) {
        this.e = supplier;
        this.f = a;
        this.j = AsyncTimer.a(e);
        this.g = completableFuture.thenCompose(object -> this.d());
    }

    private static e a(e e) {
        return null == e ? new a(null) : e;
    }

    public static AsyncTimer a(AsyncTimer asyncTimer, Supplier<CompletableFuture<?>> supplier, org.nem.core.async.a a, e e) {
        return new AsyncTimer(asyncTimer.i, supplier, a, e);
    }

    public boolean isStopped() {
        return this.g.isDone();
    }

    private CompletableFuture<?> b(int n) {
        this.j.r(n);
        return f.s(n).thenCompose(object -> this.d());
    }

    @Override
    public void close() {
        this.h.set(true);
    }

    private CompletableFuture<?> d() {
        if (this.h.get() || this.f.a()) {
            this.j.aD();
            CompletableFuture<Object> completableFuture = new CompletableFuture<Object>();
            completableFuture.complete(null);
            return completableFuture;
        }
        this.j.c();
        return this.e.get().thenAccept(object -> {
            this.j.j();
        }
        ).exceptionally(throwable -> {
            this.j.b(throwable);
            return null;
        }
        ).thenCompose(void_ -> {
            this.i.complete(null);
            return this.b(this.f.next());
        }
        );
    }

    class 1 {
    }

    static class a
    implements e {
        private a() {
        }

        @Override
        public void c() {
        }

        @Override
        public void j() {
        }

        @Override
        public void b(Throwable throwable) {
        }

        @Override
        public void r(int n) {
        }

        @Override
        public void aD() {
        }

        /* synthetic */ a(1 varnull) {
            this();
        }
    }

}