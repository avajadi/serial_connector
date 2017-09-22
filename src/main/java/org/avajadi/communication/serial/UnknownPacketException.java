package org.avajadi.communication.serial;

public class UnknownPacketException extends Throwable {
    public UnknownPacketException(float command) {
        super(String.format("Unknown packet of type %d", command));
    }
}
