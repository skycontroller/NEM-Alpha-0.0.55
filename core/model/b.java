package org.nem.core.model;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public enum b {
    cZ("UNKNOWN"),
    dz("LOCKED"),
    dA("UNLOCKED");
    
    private final String ev;

    private b(String string2) {
        this.ev = string2;
    }

    public static b p(String string) {
        if (string == null) throw new IllegalArgumentException("Invalid account status: " + string);
        for (b b : b.values()) {
            if (!b.ev.equals(string)) continue;
            return b;
        }
        throw new IllegalArgumentException("Invalid account status: " + string);
    }

    public static void a(Serializer serializer, String string, b b) {
        serializer.a(string, b.toString());
    }

    public static b j(Deserializer deserializer, String string) {
        return b.p(deserializer.k(string));
    }
}