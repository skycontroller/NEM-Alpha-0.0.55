package org.nem.core.serialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class SerializableList<T extends SerializableEntity>
implements SerializableEntity {
    private final List<T> list;

    public SerializableList(int n) {
        this.list = new ArrayList<T>(n);
    }

    public SerializableList(Collection<T> collection) {
        this(collection.size());
        collection.forEach(serializableEntity -> {
            this.f(serializableEntity);
        }
        );
    }

    public SerializableList(Deserializer deserializer, ObjectDeserializer<T> objectDeserializer) {
        this.list = deserializer.b("data", objectDeserializer);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("data", (Collection<? extends SerializableEntity>)this.list);
    }

    public final void f(T T) {
        if (null == T) {
            throw new IllegalArgumentException("cannot add null item");
        }
        this.list.add(T);
    }

    public int size() {
        return this.list.size();
    }

    public T j(int n) {
        return (SerializableEntity)this.list.get(n);
    }

    public int a(SerializableList<T> serializableList) {
        return this.b(serializableList);
    }

    private int b(SerializableList<?> serializableList) {
        int n = Math.min(this.size(), serializableList.size());
        for (int i = 0; i < n; ++i) {
            if (this.j(i).equals(serializableList.j(i))) continue;
            return i;
        }
        return n;
    }

    public Collection<T> bF() {
        return this.list;
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public boolean equals(Object object) {
        SerializableList serializableList;
        if (!(object instanceof SerializableList)) {
            return false;
        }
        return this.size() == (serializableList = (SerializableList)object).size() && this.size() == this.b(serializableList);
    }
}