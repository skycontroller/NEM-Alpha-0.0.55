package org.nem.peer.trust;

import java.util.concurrent.ConcurrentHashMap;

public class TrustParameters {
    private final ConcurrentHashMap<String, String> hc = new ConcurrentHashMap<String, String>();

    public void set(String string, String string2) {
        this.hc.put(string, string2);
    }

    public String get(String string) {
        String string2 = this.hc.get(string);
        if (null != string2) return string2;
        throw new IllegalArgumentException(string + " parameter does not exist");
    }

    public String get(String string, String string2) {
        String string3 = this.hc.get(string);
        return null == string3 ? string2 : string3;
    }

    public int z(String string) {
        return Integer.parseInt(this.get(string));
    }

    public int c(String string, int n) {
        String string2 = this.get(string, null);
        return null == string2 ? n : Integer.parseInt(string2);
    }

    public double A(String string) {
        return Double.parseDouble(this.get(string));
    }

    public double b(String string, double d) {
        String string2 = this.get(string, null);
        return null == string2 ? d : Double.parseDouble(string2);
    }
}