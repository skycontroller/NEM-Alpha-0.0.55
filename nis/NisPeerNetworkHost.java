package org.nem.nis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.nem.core.async.AsyncTimer;
import org.nem.core.async.a;
import org.nem.core.async.b;
import org.nem.core.async.c;
import org.nem.core.async.d;
import org.nem.core.async.e;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.model.Block;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.b;
import org.nem.core.time.b;
import org.nem.deploy.CommonStarter;
import org.nem.nis.BlockChain;
import org.nem.nis.FatalConfigException;
import org.nem.nis.NisAsyncTimerVisitor;
import org.nem.nis.audit.AuditCollection;
import org.nem.nis.cx;
import org.nem.peer.BlockSynchronizer;
import org.nem.peer.Config;
import org.nem.peer.PeerNetwork;
import org.nem.peer.PeerNetworkServices;
import org.nem.peer.SecureSerializableEntity;
import org.nem.peer.connect.HttpConnectorPool;
import org.nem.peer.connect.PeerConnector;
import org.nem.peer.connect.SyncConnectorPool;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.trust.score.NodeExperiencesPair;
import org.springframework.beans.factory.annotation.Autowired;

public class NisPeerNetworkHost
implements AutoCloseable {
    private static final int dD = 1000;
    private static final int dE = 60000;
    private static final int dF = 3600000;
    private static final int dG = 200;
    private static final int dH = 1000;
    private static final int dI = 300000;
    private static final int dJ = 43200000;
    private static final int dK = 5000;
    private static final int dL = 300000;
    private static final int dM = 5000;
    private static final int dN = 3000;
    private static final int dO = 3600000;
    private static final int dP = 300000;
    private static final int hD = 50;
    private final org.nem.core.serialization.b cG;
    private final BlockChain dB;
    private final cx dQ;
    private AsyncTimer dR;
    private PeerNetworkHost dS;
    private final AtomicBoolean hE = new AtomicBoolean(false);
    private final List<NisAsyncTimerVisitor> hF = new ArrayList<NisAsyncTimerVisitor>();
    private final AuditCollection hG = NisPeerNetworkHost.eV();
    private final AuditCollection hH = NisPeerNetworkHost.eV();

    @Autowired(required=1)
    public NisPeerNetworkHost(org.nem.core.serialization.b b, BlockChain blockChain) {
        this.cG = b;
        this.dB = blockChain;
        this.dQ = new cx(this.dB);
    }

    public CompletableFuture b(Node node) {
        Config config = new Config(node, NisPeerNetworkHost.o("peers-config.json"), CommonStarter.cT.getVersion());
        return this.a(config);
    }

    public CompletableFuture a(Config config) {
        if (this.hE.compareAndSet(false, true)) return PeerNetwork.a(config, this.cu()).handle((peerNetwork, throwable) -> {
            if (null != throwable) {
                this.hE.set(false);
                throw new IllegalStateException("network boot failed", throwable);
            }
            this.dS = new PeerNetworkHost(peerNetwork);
            this.hF.addAll(this.dS.eU());
            NisAsyncTimerVisitor nisAsyncTimerVisitor = PeerNetworkHost.B("FORAGING");
            this.hF.add(nisAsyncTimerVisitor);
            this.dR = new AsyncTimer(this.dS.a(() -> {
                Block block = this.dB.ce();
                if (null == block) {
                    return;
                }
                SecureSerializableEntity<Block> secureSerializableEntity = new SecureSerializableEntity<Block>(block, this.dS.ct().dC().dW());
                this.ct().a(NodeApiId.gv, secureSerializableEntity);
            }
            ), 5000, (a)new d(3000), (e)nisAsyncTimerVisitor);
            return null;
        }
        );
        throw new IllegalStateException("network boot was already attempted");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    private static JSONObject o(String string) {
        InputStream object2;
        Throwable throwable;
        try {
            object2 = NisPeerNetworkHost.class.getClassLoader().getResourceAsStream(string);
            throwable = null;
            try {
                if (null != object2) return (JSONObject)JSONValue.parse((InputStream)object2);
                throw new FatalConfigException(String.format("Configuration file <%s> not available", string));
            }
            catch (Throwable var3_5) {
                throwable = var3_5;
                throw var3_5;
            }
        }
        catch (Exception var1_2) {
            throw new FatalConfigException("Exception encountered while loading config", var1_2);
        }
        finally {
            if (object2 != null) {
                if (throwable != null) {
                    try {
                        object2.close();
                    }
                    catch (Throwable var4_6) {
                        throwable.addSuppressed(var4_6);
                    }
                } else {
                    object2.close();
                }
            }
        }
    }

    public PeerNetwork ct() {
        if (null != this.dS) return this.dS.ct();
        throw new IllegalStateException("network has not been booted yet");
    }

    public AuditCollection eS() {
        return this.hG;
    }

    public AuditCollection eT() {
        return this.hH;
    }

    public int a(Node node) {
        return this.dQ.a(node);
    }

    public List<NisAsyncTimerVisitor> eU() {
        ArrayList<NisAsyncTimerVisitor> arrayList = new ArrayList<NisAsyncTimerVisitor>();
        arrayList.addAll(this.hF);
        return arrayList;
    }

    @Override
    public void close() {
        if (null != this.dR) {
            this.dR.close();
        }
        if (null == this.dS) return;
        this.dS.close();
    }

    private PeerNetworkServices cu() {
        HttpConnectorPool httpConnectorPool = new HttpConnectorPool(this.eS());
        PeerConnector peerConnector = httpConnectorPool.b(this.cG);
        return new PeerNetworkServices(peerConnector, httpConnectorPool, this.dQ);
    }

    private static AuditCollection eV() {
        return new AuditCollection(50, CommonStarter.o);
    }

    static class PeerNetworkHost
    implements AutoCloseable {
        private final PeerNetwork dT;
        private final AsyncTimer dU;
        private final List<NisAsyncTimerVisitor> hF;
        private final List<AsyncTimer> dV;
        private final Executor executor = Executors.newCachedThreadPool();

        public PeerNetworkHost(PeerNetwork peerNetwork) {
            this.dT = peerNetwork;
            this.hF = new ArrayList<NisAsyncTimerVisitor>();
            this.hF.add(PeerNetworkHost.B("REFRESH"));
            this.dU = new AsyncTimer(() -> this.dT.dM(), 200, PeerNetworkHost.cx(), (e)this.hF.get(0));
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
            NisAsyncTimerVisitor nisAsyncTimerVisitor = PeerNetworkHost.B(string);
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

}
