package org.nem.core.utils;

public class i {
    public static boolean isNullOrEmpty(String string) {
        return null == string || 0 == string.length();
    }

    public static boolean n(String string) {
        if (i.isNullOrEmpty(string)) {
            return true;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isWhitespace(string.charAt(i))) continue;
            return false;
        }
        return true;
    }
}