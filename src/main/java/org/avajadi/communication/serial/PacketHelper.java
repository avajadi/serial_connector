package org.avajadi.communication.serial;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

/**
 * A small collection of utility methods to help in the conversion between serial data and raw data type, mostly numerical. Default endianness is LITTLE_ENDIAN.
 */
public class PacketHelper {
    public final static ByteOrder DEFAULT_ENDIAN = LITTLE_ENDIAN;
    /**
     * Convert an int to a byte array with default endianness (LITTLE_ENDIAN)
     * @param integer
     * @return
     */
    public static byte[] intToByteArray( int integer ) {
        return intToByteArray( integer, DEFAULT_ENDIAN );
    }

    /**
     * Convert an int to a byte array
     * @param myInteger
     * @param endianNess
     * @return
     */
    public static  byte[] intToByteArray(int myInteger, ByteOrder endianNess ){
        return ByteBuffer.allocate(4).order(endianNess).putInt(myInteger).array();
    }

    /**
     * Convert a byte array to an int with default endianness
     * @param byteArray
     * @return
     */
    public static int byteArrayToInt(byte [] byteArray) {
        return byteArrayToInt(byteArray, DEFAULT_ENDIAN );
    }

    /**
     * Convert a byte array to an int
     * @param byteArray
     * @param endianNess
     * @return
     */
    public static int byteArrayToInt(byte[] byteArray, ByteOrder endianNess ) {
        return ByteBuffer.wrap(byteArray).order(endianNess).getInt();
    }

    /**
     * Convert byte array to float with default endianness
     * @param byteArray
     * @return
     */
    public static float byteArrayToFloat( byte[] byteArray ) {
        return byteArrayToFloat( byteArray, DEFAULT_ENDIAN );
    }

    /**
     *
     * @param byteArray
     * @param endianNess
     * @return
     */
    public static float byteArrayToFloat( byte[] byteArray, ByteOrder endianNess  ) {
        return ByteBuffer.wrap(byteArray).order( endianNess ).getFloat();
    }

    public static byte[] floatToByteArray( float floatValue ) {
        return floatToByteArray( floatValue, DEFAULT_ENDIAN );
    }

    public static byte[] floatToByteArray(float floatValue, ByteOrder endianness) {
        return ByteBuffer.allocate(4).order(endianness).putFloat( floatValue ).array();
    }
}
