package org.nem.nis;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nem.core.async.e;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;

public class NisAsyncTimerVisitor
implements e,
SerializableEntity {
    private static final Logger LOGGER = Logger.getLogger(NisAsyncTimerVisitor.class.getName());
    private final String ht;
    private final b ao;
    private int hu;
    private int hv;
    private int hw;
    private TimeInstant hx = TimeInstant.cO;
    private int hy;
    private int hz;
    private int hA;

    public NisAsyncTimerVisitor(String string, b b) {
        this.ht = string;
        this.ao = b;
    }

    public String cA() {
        return this.ht;
    }

    public int dd() {
        return this.hu;
    }

    public int dj() {
        return this.hv;
    }

    public int eJ() {
        return this.hw;
    }

    public TimeInstant eK() {
        return this.hx;
    }

    public int eL() {
        return this.hy;
    }

    public int eM() {
        return this.hz;
    }

    public int eN() {
        return this.eP() > 0 ? this.hA / this.eP() : 0;
    }

    public boolean eO() {
        return this.hu > this.eP();
    }

    private int eP() {
        return this.hv + this.hw;
    }

    @Override
    public void c() {
        this.log("executing");
        this.hx = this.ao.ac();
        ++this.hu;
    }

    @Override
    public void j() {
        this.eQ();
        ++this.hv;
    }

    @Override
    public void b(Throwable throwable) {
        NisAsyncTimerVisitor.LOGGER.log(Level.WARNING, String.format("Timer %s raised exception: %s", "<name>", throwable.getMessage()), throwable);
        this.eQ();
        ++this.hw;
    }

    @Override
    public void r(int n) {
        this.log(String.format("sleeping for %d ms", n));
        this.hz = n;
    }

    @Override
    public void aD() {
        this.log("stopping");
    }

    private void eQ() {
        TimeInstant timeInstant = this.ao.ac();
        this.hy = timeInstant.b(this.hx);
        this.hA+=this.hy;
    }

    private void log(String string) {
        NisAsyncTimerVisitor.LOGGER.fine(String.format("[%d] Timer %s: %s", Thread.currentThread().getId(), this.ht, string));
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("name", this.cA());
        serializer.b("executions", this.dd());
        serializer.b("successes", this.dj());
        serializer.b("failures", this.eJ());
        serializer.b("last-delay-time", this.eM());
        TimeInstant.a(serializer, "last-operation-start-time", this.eK());
        serializer.b("last-operation-time", this.eL());
        serializer.b("average-operation-time", this.eN());
        serializer.b("is-executing", this.eO() ? 1 : 0);
    }
}
