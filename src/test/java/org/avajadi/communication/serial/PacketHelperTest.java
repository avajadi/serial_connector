package org.avajadi.communication.serial;

import org.testng.annotations.Test;

import java.nio.ByteOrder;

import static org.testng.Assert.*;

public class PacketHelperTest {
    @Test
    public void testIntToByteArray() throws Exception {
        int minInt = 1;
        byte[] minByte = PacketHelper.intToByteArray( minInt );
        assertEquals( (int)minByte[0], 1);
    }

    @Test
    public void testIntToByteArrayBigEndian() throws Exception {
        int minInt = 1;
        byte[] minByte = PacketHelper.intToByteArray( minInt, ByteOrder.BIG_ENDIAN );
        assertEquals( (int)minByte[3], 1);
    }

    @Test
    public void testByteArrayToInt() throws Exception {
        byte[] intByte = {1,0,0,0};
        int byteInt = PacketHelper.byteArrayToInt( intByte );
        assertEquals( byteInt, 1 );
    }

    @Test
    public void testByteArrayToIntBigEndian() throws Exception {
        byte[] intByte = {0,0,0,1};
        int byteInt = PacketHelper.byteArrayToInt( intByte, ByteOrder.BIG_ENDIAN );
        assertEquals( byteInt, 1 );
    }

    @Test
    public void testByteArrayToFloat() throws Exception {
        float byteFloat = 7.3f;
        byte[] floatByte = PacketHelper.floatToByteArray(byteFloat);
        float floatByteFloat = PacketHelper.byteArrayToFloat(floatByte);
        assertEquals(floatByteFloat, byteFloat );
    }

    @Test
    public void testByteArrayToFloat1() throws Exception {
    }

}