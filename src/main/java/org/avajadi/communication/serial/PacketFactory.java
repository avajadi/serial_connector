package org.avajadi.communication.serial;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class PacketFactory {
    private HashMap<Integer, Packet> packets;

    public Packet fromBytes(byte[] byteArray) throws UnknownPacketException {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray).order(PacketHelper.DEFAULT_ENDIAN);
        float command = buffer.getInt();
        Packet packet = packets.get(command);
        if (packet == null) {
            throw new UnknownPacketException(command);
        }
        return packet.fromBytes(buffer);
    }
}
