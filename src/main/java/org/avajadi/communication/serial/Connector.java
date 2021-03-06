package org.avajadi.communication.serial;

import jssc.*;
import org.avajadi.velleman.vm8090.Cmd;
import org.avajadi.velleman.vm8090.Packet;
import org.avajadi.velleman.vm8090.PacketListener;

import java.util.ArrayList;
import java.util.List;

public class Connector implements SerialPortEventListener {
    private final List<PacketListener> packetListeners = new ArrayList<PacketListener>();
    private SerialPort serialPort;
    private String portDev = null;

    public static void main(String[] args) throws DeviceNotFoundException, SerialPortException {
        Connector connector = new Connector();
        connector.connect();
        System.out.println(connector.getPortDev());
        connector.addPacketListener(packet -> {
            System.out.println("Packet arrived");
            System.out.println(packet);
        });
    }

    public void connect() throws DeviceNotFoundException {
        String[] portNames = SerialPortList.getPortNames();
        Packet probe = new SimplePacket(0x0F0F);
        for (String portName : portNames) {
            serialPort = new SerialPort(portName);
            try {
                serialPort.openPort();// Open serial port
                serialPort.setParams(SerialPort.BAUDRATE_19200,
                        SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                serialPort.writeBytes(probe.toBytes());
                byte[] response = serialPort.readBytes(7);
                if (response[1] == 0x71) {
                    setPortDev(portName);
                    serialPort.addEventListener(this);
                    return;
                }
            } catch (SerialPortException ex) {
                System.out.println(ex);

            }
        }
        throw new DeviceNotFoundException("No vm8090 found");
    }

    public void send(Packet packet) throws SerialPortException {
        serialPort.writeBytes(packet.toBytes());
    }

    public String getPortDev() {
        return portDev;
    }

    public void setPortDev(String portDev) {
        this.portDev = portDev;
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        //TODO Handle other types of events (assuming type 1 atm)
        int byteCount = serialPortEvent.getEventValue();
        while (byteCount > 6) {
            byte[] data;
            try {
                data = serialPort.readBytes(7);
                byteCount -= 7;
                Packet packet = new Packet(data);
                for (PacketListener packetListener : getPacketListeners()) {
                    packetListener.processPacket(packet);
                }
            } catch (SerialPortException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private List<PacketListener> getPacketListeners() {
        return packetListeners;
    }

    public void addPacketListener(PacketListener listener) {
        packetListeners.add(listener);
    }

    public void disconnect() {
        try {
            serialPort.purgePort(1);
            serialPort.closePort();// Close serial port
        } catch (SerialPortException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
