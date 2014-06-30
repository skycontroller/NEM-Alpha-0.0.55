package org.nem.core.messages;

import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Message;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;

public class a {
    public static final ObjectDeserializer<Message> D = new ObjectDeserializer<Message>(){

        @Override
        public Message deserialize(Deserializer deserializer) {
            return a.deserialize(deserializer);
        }
    };

    private static Message deserialize(Deserializer deserializer) {
        int n = deserializer.f("type");
        switch (n) {
            case 1: {
                return new PlainMessage(deserializer);
            }
            case 2: {
                return new SecureMessage(deserializer);
            }
        }
        throw new IllegalArgumentException("Unknown message type: " + n);
    }

}