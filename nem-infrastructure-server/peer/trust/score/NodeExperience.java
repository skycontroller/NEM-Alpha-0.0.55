package org.nem.peer.trust.score;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.trust.score.PositiveLong;

public class NodeExperience
implements SerializableEntity {
    private final PositiveLong hd = new PositiveLong(0);
    private final PositiveLong he = new PositiveLong(0);

    public NodeExperience() {
    }

    public NodeExperience(long l, long l2) {
        this.eA().set(l);
        this.eB().set(l2);
    }

    public NodeExperience(Deserializer deserializer) {
        this.eA().set(deserializer.g("s"));
        this.eB().set(deserializer.g("f"));
    }

    public PositiveLong eA() {
        return this.hd;
    }

    public PositiveLong eB() {
        return this.he;
    }

    public long eC() {
        return this.hd.get() + this.eB().get();
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("s", this.hd.get());
        serializer.a("f", this.eB().get());
    }

    public int hashCode() {
        return Long.valueOf(this.eA().get() ^ this.eB().get()).intValue();
    }

    public boolean equals(Object object) {
        NodeExperience nodeExperience;
        if (!(object instanceof NodeExperience)) {
            return false;
        }
        return this.hd.get() == (nodeExperience = (NodeExperience)object).eA().get() && this.eB().get() == nodeExperience.eB().get();
    }

    public String toString() {
        return String.format("success: %d, failure: %d", this.eA().get(), this.eB().get());
    }
}