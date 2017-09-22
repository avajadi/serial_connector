package org.avajadi.communication.serial;

import java.nio.ByteBuffer;

public interface Packet {
    byte[] toBytes();
    Packet fromBytes( ByteBuffer buffer );
}
