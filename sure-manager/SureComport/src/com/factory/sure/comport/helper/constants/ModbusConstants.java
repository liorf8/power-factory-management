/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.helper.constants;

/**
 *
 * @author MinhLuan
 */
public class ModbusConstants {

    /**
     * The number of generators in the factory
     */
    public static final int NUM_OF_GENERATORS = 1;
    /**
     * The Modbus address of the Master board.
     */
    public static final byte GENERATOR_1_MODBUS_ADDRESS = 0x12;    //The modbus address of the board
    /**
     * The Modbus address of the Master board.
     */
    public static final byte GENERATOR_2_MODBUS_ADDRESS = 0x13;    //The modbus address of the board
    /**
     * The Modbus address of the Master board.
     */
    public static final byte GENERATOR_3_MODBUS_ADDRESS = 0x14;    //The modbus address of the board
    /**
     * Cycle time: time required to get all of the information from a Master
     * board
     */
    public static final int MASTER_CYCLE_TIME = 2500;   // In milliseconds
    /**
     * The time interval to request next values form Com port.
     */
    public static final int TIME_INTERVAL = 200;    // In milliseconds
    /**
     * The history buffer capacity
     */
    public static final int HISTORY_CAPACITY = 200;
    /**
     * The Modbus request function code.
     */
    public static final byte MODBUS_REQUEST_FUNCTION_CODE = 0x03;
    /**
     * The Modbus request length of data per message calculated by the number of
     * int16_t value
     */
    public static final int MODBUS_REQUEST_MAX_LENGTH_OF_DATA = 0x0C;
    /**
     * The Modbus request message length
     */
    public static final int MOBUS_REQUEST_MESSAGE_LENGTH = 0x08;
    /**
     * The Modbus reply function code.
     */
    public static final byte MODBUS_REPLY_FUNCTION_CODE = 0x06;
    /**
     * The number of segment
     */
    public static final int SEGMENT_NUMBER = 8;
    // The segment 1
    public static final int SEGMENT_1_ADDRESS = 60;
    public static final int SEGMENT_1_WORD_COUNT = 12;
    // The segment 2
    public static final int SEGMENT_2_ADDRESS = 72;
    public static final int SEGMENT_2_WORD_COUNT = 12;
    // The segment 3
    public static final int SEGMENT_3_ADDRESS = 84;
    public static final int SEGMENT_3_WORD_COUNT = 12;
    // The segment 4
    public static final int SEGMENT_4_ADDRESS = 96;
    public static final int SEGMENT_4_WORD_COUNT = 2;
    // The segment 5
    public static final int SEGMENT_5_ADDRESS = 111;
    public static final int SEGMENT_5_WORD_COUNT = 2;
    // The segment 6
    public static final int SEGMENT_6_ADDRESS = 132;
    public static final int SEGMENT_6_WORD_COUNT = 12;
    // The segment 7
    public static final int SEGMENT_7_ADDRESS = 144;
    public static final int SEGMENT_7_WORD_COUNT = 8;
    // The segment 8
    public static final int SEGMENT_8_ADDRESS = 156;
    public static final int SEGMENT_8_WORD_COUNT = 4;
}
