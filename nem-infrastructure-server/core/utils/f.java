package org.nem.core.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

public class f {
    public static DecimalFormat bK() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#0.000", decimalFormatSymbols);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat;
    }

    public static DecimalFormat p(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("decimalPlaces must be non-negative");
        }
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#0");
        if (n > 0) {
            stringBuilder.append('.');
            object = new char[n];
            Arrays.fill((char[])object, '0');
            stringBuilder.append((char[])object);
        }
        Object object = new DecimalFormat(stringBuilder.toString(), decimalFormatSymbols);
        object.setGroupingUsed(false);
        return object;
    }
}