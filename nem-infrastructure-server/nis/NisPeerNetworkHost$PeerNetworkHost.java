package org.nem.nis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.nem.core.async.AsyncTimer;
import org.nem.core.async.a;
import org.nem.core.async.b;
import org.nem.core.async.c;
import org.nem.core.async.d;
import org.nem.core.async.e;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.time.b;
import org.nem.deploy.CommonStarter;
import org.nem.nis.NisAsyncTimerVisitor;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.peer.PeerNetwork;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.trust.score.NodeExperiencesPair;

class NisPeerNetworkHost$PeerNetworkHost
implements AutoCloseable {
    private final PeerNetwork dT;
    private final AsyncTimer dU;
    private final List<NisAsyncTimerVisitor> hF;
    private final List<AsyncTimer> dV;
    private final Executor executor = Executors.newCachedThreadPool();

    public NisPeerNetworkHost$PeerNetworkHost(PeerNetwork peerNetwork) {
        this.dT = peerNetwork;
        this.hF = new ArrayList<NisAsyncTimerVisitor>();
        this.hF.add(NisPeerNetworkHost$PeerNetworkHost.B("REFRESH"));
        this.dU = new AsyncTimer(() -> this.dT.dM(), 200, NisPeerNetworkHost$PeerNetworkHost.cx(), (e)this.hF.get(0));
        AsyncTimer[] arrasyncTimer = new AsyncTimer[4];
        arrasyncTimer[0] = this.a(() -> this.dT.a(NodeApiId.gu, peerNetwork.dJ()), 300000, "BROADCAST");
        arrasyncTimer[1] = this.a(this.a(() -> {
            this.dT.dN();
        }
        ), 5000, "SYNC");
        arrasyncTimer[2] = this.a(this.a(() -> {
            this.dT.dO();
        }
        ), 3600000, "PRUNING INACTIVE NODES");
        arrasyncTimer[3] = this.a(this.a(() -> {
            this.dT.dP();
        }
        ), 300000, "UPDATING LOCAL NODE ENDPOINT");
        this.dV = Arrays.asList(arrasyncTimer);
    }

    private static a cx() {
        List<a> list = Arrays.asList(c.a(1000, 300000, 43200000), new d(300000));
        return new b(list);
    }

    private AsyncTimer a(Supplier<CompletableFuture<?>> supplier, int n, String string) {
        NisAsyncTimerVisitor nisAsyncTimerVisitor = NisPeerNetworkHost$PeerNetworkHost.B(string);
        this.hF.add(nisAsyncTimerVisitor);
        return AsyncTimer.a(this.dU, supplier, new d(n), nisAsyncTimerVisitor);
    }

    public PeerNetwork ct() {
        return this.dT;
    }

    public List<NisAsyncTimerVisitor> eU() {
        return this.hF;
    }

    @Override
    public void close() {
        this.dU.close();
        this.dV.forEach(asyncTimer -> {
            asyncTimer.close();
        }
        );
    }

    public Supplier<CompletableFuture<?>> a(Runnable runnable) {
        return () -> CompletableFuture.runAsync(runnable, this.executor);
    }

    public static NisAsyncTimerVisitor B(String string) {
        return new NisAsyncTimerVisitor(string, CommonStarter.o);
    }
}
