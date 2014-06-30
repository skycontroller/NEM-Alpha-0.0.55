package org.nem.deploy;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class a
extends ConsoleHandler {
    private static final String POSTFIX = "\u001b[0m";
    private static final Map<Level, String> cR = a.bL();
    private static final String cS = a.cR.get(Level.SEVERE);

    @Override
    public void publish(LogRecord logRecord) {
        String string = a.bL().getOrDefault(logRecord.getLevel(), a.cS);
        logRecord.setMessage(string + logRecord.getMessage() + "\u001b[0m");
        super.publish(logRecord);
    }

    private static Map<Level, String> bL() {
        HashMap<Level, String> hashMap = new HashMap<Level, String>();
        hashMap.put(Level.SEVERE, "\u001b[0;31m");
        hashMap.put(Level.WARNING, "\u001b[0;33m");
        hashMap.put(Level.INFO, "\u001b[0;32m");
        hashMap.put(Level.CONFIG, "\u001b[0;37m");
        hashMap.put(Level.FINE, "\u001b[0;34m");
        hashMap.put(Level.FINER, "\u001b[0;34m");
        hashMap.put(Level.FINEST, "\u001b[0;34m");
        return hashMap;
    }
}