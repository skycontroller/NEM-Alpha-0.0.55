package org.nem.core.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class a<TKey, TValue> {
    private final Map<TKey, Map<TKey, TValue>> cP = new ConcurrentHashMap<TKey, Map<TKey, TValue>>();

    public TValue a(TKey TKey, TKey TKey2) {
        Map<TKey, TValue> map = this.e(TKey);
        TValue TValue = map.get(TKey2);
        if (null != TValue) return TValue;
        TValue = this.bJ();
        map.put(TKey2, TValue);
        return TValue;
    }

    public Map<TKey, TValue> e(TKey TKey) {
        Map<TKey, TValue> map = this.cP.get(TKey);
        if (null != map) return map;
        map = new ConcurrentHashMap<TKey, TValue>();
        this.cP.put(TKey, map);
        return map;
    }

    protected abstract TValue bJ();
}