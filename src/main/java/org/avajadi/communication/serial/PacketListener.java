package org.avajadi.communication.serial;

public interface PacketListener {
    void processPacket(Packet packet);
}
