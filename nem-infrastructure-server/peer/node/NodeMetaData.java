package org.nem.peer.node;

import java.util.Arrays;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class NodeMetaData
implements SerializableEntity {
    private final String gG;
    private final String gH;
    private final String version;

    public NodeMetaData(String string, String string2, String string3) {
        this.gG = string;
        this.gH = string2;
        this.version = string3;
    }

    public NodeMetaData(Deserializer deserializer) {
        this.gG = deserializer.k("platform");
        this.gH = deserializer.k("application");
        this.version = deserializer.k("version");
    }

    public String ek() {
        return this.gG;
    }

    public String el() {
        return this.gH;
    }

    public String getVersion() {
        return this.version;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("platform", this.gG);
        serializer.a("application", this.gH);
        serializer.a("version", this.version);
    }

    public int hashCode() {
        return Arrays.hashCode(this.em());
    }

    public boolean equals(Object object) {
        if (!(object instanceof NodeMetaData)) {
            return false;
        }
        NodeMetaData nodeMetaData = (NodeMetaData)object;
        return Arrays.equals(this.em(), nodeMetaData.em());
    }

    private String[] em() {
        return new String[]{this.gG, this.gH, this.version};
    }
}