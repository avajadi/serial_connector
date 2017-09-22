package org.avajadi.communication.serial;

import java.nio.ByteBuffer;

public class SimplePacket implements Packet {
    private final int command;
    public SimplePacket(int command) {
        this.command = command;
    }

    @Override
    public byte[] toBytes() {
        return PacketHelper.intToByteArray(command);
    }

    @Override
    public Packet fromBytes(ByteBuffer buffer) {
        return new SimplePacket(command);
    }
}
