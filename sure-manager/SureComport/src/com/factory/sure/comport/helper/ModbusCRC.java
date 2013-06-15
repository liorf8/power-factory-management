package com.factory.sure.comport.helper;

public class ModbusCRC {

    private static final int POLINOMIAL = 0x0000A001;

    public static int doCRC16(byte[] array) {
        return doCRC16(array, 0, array.length); // Calls doCRC16(byte[] array, int off, int len)
    }

    /**
     * This does the actual calculation.
     */
    public static int doCRC16(byte[] array, int off, int len) {
        int crcValue = 0xFFFF;
        int k, l;

        for (k = 0; k < len; k++) {
            crcValue ^= (array[k] & 0x000000ff);
            for (l = 0; l < 8; l++) {
                if ((crcValue & 0x01) != 0) {
                    crcValue >>= 1;
                    crcValue ^= POLINOMIAL;
                } else {
                    crcValue >>= 1;
                }
            }
        }
        return crcValue;
    }

    public static int doCRC16(char[] array) {
        return doCRC16(array, 0, array.length); // Calls doCRC16(char[] array, int off, int len)
    }

    public static int doCRC16(char[] array, int off, int len) {
        String str = new String(array, off, len); // Wraps the char array with a String object.
        byte[] bArray = str.getBytes(); // Converts this String into a sequence of bytes using the platform's default charset.

        return doCRC16(bArray, 0, bArray.length); // Calls doCRC16(byte[] array, int off, int len)
    }
}