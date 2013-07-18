/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.uart;

import com.factory.sure.comport.data.GeneratorData;
import com.factory.sure.comport.data.SharedObject;
import com.factory.sure.comport.helper.ModbusCRC;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author MinhLuan
 */
public class SerialPortReader implements SerialPortEventListener {

    private SerialPort m_pSerialPort;
    private SharedObject m_pSharedObject;
    // The lookup content
    private InstanceContent m_pInstanceContent;

    public SerialPortReader(SerialPort serialPort, SharedObject sharedObject, InstanceContent instanceContent) {
        this.m_pSerialPort = serialPort;
        this.m_pSharedObject = sharedObject;
        this.m_pInstanceContent = instanceContent;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {//If data is available
            ReentrantLock lock = this.m_pSharedObject.getLock();
            int comStatus = this.m_pSharedObject.getComStatus();    // Not very exact, but can be used to approximate the value
            if (event.getEventValue() == crcLength(comStatus) + 2) {
                try {
                    byte buffer[] = m_pSerialPort.readBytes();
                    lock.lock();
                    try {
                        comStatus = this.m_pSharedObject.getComStatus();
                        int crcLength = crcLength(comStatus);
                        //Calculate the CRC
                        int crc = ModbusCRC.doCRC16(buffer, 0, crcLength);
                        if (((byte) (crc & 0x000000ff)) == buffer[crcLength]
                                && ((byte) ((crc & 0x0000ff00) >> 8)) == buffer[crcLength + 1]) {
                            if (buffer[1] == ModbusConstants.MODBUS_REQUEST_FUNCTION_CODE) {
                                switch (comStatus) {
                                    case 1:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_1_WORD_COUNT << 1)) {
                                            processSegment1(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 2:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_2_WORD_COUNT << 1)) {
                                            processSegment2(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 3:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_3_WORD_COUNT << 1)) {
                                            processSegment3(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 4:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_4_WORD_COUNT << 1)) {
                                            processSegment4(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 5:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_5_WORD_COUNT << 1)) {
                                            processSegment5(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 6:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_6_WORD_COUNT << 1)) {
                                            processSegment6(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 7:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_7_WORD_COUNT << 1)) {
                                            processSegment7(this.m_pSharedObject.getCurrentGeneratorData(), buffer);
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    case 8:
                                        if (buffer[2] == (ModbusConstants.SEGMENT_8_WORD_COUNT << 1)) {
                                            processSegment8(this.m_pSharedObject.getCurrentGeneratorData(), buffer);

                                            // Update the lookup content
                                            updateLookup();
                                        } else {
                                            // The receive size is not valid
                                            System.err.println("Segment " + comStatus + ": Wrong received size");
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            } else {    // (buffer[1] != ModbusConstants.MODBUS_REQUEST_FUNCTION_CODE)
                                System.err.println("Segment " + comStatus + ": Wrong function code");
                            }   // End if (buffer[1] == ModbusConstants.MODBUS_REQUEST_FUNCTION_CODE)
//                            System.out.println("Segment " + comStatus + ": Right CRC");

                        } else {
                            System.err.println("Segment " + comStatus + ": Wrong CRC");
                        }
                    } finally {
                        lock.unlock();
                    }
                } catch (SerialPortException ex) {
                    Logger.getLogger(SerialPortReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void processSegment1(GeneratorData generatorData, byte[] buffer) {

        generatorData.setG_Ua(twoBytesToShort(buffer, 3));
        generatorData.setG_Ub(twoBytesToShort(buffer, 5));
        generatorData.setG_Uc(twoBytesToShort(buffer, 7));
        generatorData.setG_Uab(twoBytesToShort(buffer, 9));
        generatorData.setG_Ubc(twoBytesToShort(buffer, 11));
        generatorData.setG_Uca(twoBytesToShort(buffer, 13));
        generatorData.setL_Ua(twoBytesToShort(buffer, 15));
        generatorData.setL_Ub(twoBytesToShort(buffer, 17));
        generatorData.setL_Uc(twoBytesToShort(buffer, 19));
        generatorData.setL_Uab(twoBytesToShort(buffer, 21));
        generatorData.setL_Ubc(twoBytesToShort(buffer, 23));
        generatorData.setL_Uca(twoBytesToShort(buffer, 25));
    }

    private void processSegment2(GeneratorData generatorData, byte[] buffer) {
//        generatorData.setGL_Ua(new Short((short) ((buffer[3] << 8) + buffer[4])));
//        generatorData.setGL_Ub(new Short((short) ((buffer[5] << 8) + buffer[6])));
//        generatorData.setGL_Uc(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setIa(twoBytesToShort(buffer, 9));
        generatorData.setIb(twoBytesToShort(buffer, 11));
        generatorData.setIc(twoBytesToShort(buffer, 13));
        generatorData.setPa(twoBytesToShort(buffer, 15));
        generatorData.setPb(twoBytesToShort(buffer, 17));
        generatorData.setPc(twoBytesToShort(buffer, 19));
        generatorData.setQa(twoBytesToShort(buffer, 21));
        generatorData.setQb(twoBytesToShort(buffer, 23));
        generatorData.setQc(twoBytesToShort(buffer, 25));
    }

    private void processSegment3(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSa(twoBytesToShort(buffer, 3));
        generatorData.setSb(twoBytesToShort(buffer, 5));
        generatorData.setSc(twoBytesToShort(buffer, 7));
        generatorData.setPFa(twoBytesToShort(buffer, 9));
        generatorData.setPFb(twoBytesToShort(buffer, 11));
        generatorData.setPFc(twoBytesToShort(buffer, 13));
        generatorData.setP3Phases(twoBytesToShort(buffer, 15));
        generatorData.setQ3Phases(twoBytesToShort(buffer, 17));
        generatorData.setS3Phases(twoBytesToShort(buffer, 19));
        generatorData.setPF3Phases(twoBytesToShort(buffer, 21));
        generatorData.setG_RealFreq(twoBytesToShort(buffer, 23));
        generatorData.setL_RealFreq(twoBytesToShort(buffer, 25));
    }

    private void processSegment4(GeneratorData generatorData, byte[] buffer) {
        generatorData.setI0(twoBytesToShort(buffer, 3));
        generatorData.setPowerEnergy(twoBytesToShort(buffer, 5));
    }

    private void processSegment5(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSpeedGuardStatus(twoBytesToShort(buffer, 3));
        generatorData.setRPM(twoBytesToShort(buffer, 5));
    }

    private void processSegment6(GeneratorData generatorData, byte[] buffer) {
        generatorData.setTemperature1(twoBytesToShort(buffer, 3));
        generatorData.setTemperature2(twoBytesToShort(buffer, 5));
        generatorData.setTemperature3(twoBytesToShort(buffer, 7));
        generatorData.setTemperature4(twoBytesToShort(buffer, 9));
        generatorData.setTemperature5(twoBytesToShort(buffer, 11));
        generatorData.setTemperature6(twoBytesToShort(buffer, 13));
        generatorData.setTemperature7(twoBytesToShort(buffer, 15));
        generatorData.setTemperature8(twoBytesToShort(buffer, 17));
        generatorData.setTemperature9(twoBytesToShort(buffer, 19));
        generatorData.setTemperature10(twoBytesToShort(buffer, 21));
        generatorData.setTemperature11(twoBytesToShort(buffer, 23));
        generatorData.setTemperature12(twoBytesToShort(buffer, 25));
    }

    private void processSegment7(GeneratorData generatorData, byte[] buffer) {
        generatorData.setPress1(twoBytesToShort(buffer, 3));
        generatorData.setPress2(twoBytesToShort(buffer, 5));
        generatorData.setPress3(twoBytesToShort(buffer, 7));
        generatorData.setPress4(twoBytesToShort(buffer, 9));
        generatorData.setContactGroup1(twoBytesToShort(buffer, 11));
        generatorData.setContactGroup2(twoBytesToShort(buffer, 13));
        generatorData.setContactGroup3(twoBytesToShort(buffer, 15));
        generatorData.setContactGroup4(twoBytesToShort(buffer, 17));
    }

    private void processSegment8(GeneratorData generatorData, byte[] buffer) {
        generatorData.setWaterOpenAngle(twoBytesToShort(buffer, 3));
        generatorData.setFuelLevel1(twoBytesToShort(buffer, 5));
        generatorData.setFuelLevel2(twoBytesToShort(buffer, 7));
        generatorData.setValvePlace(twoBytesToShort(buffer, 9));
    }

    /**
     * Convert two continuous bytes: buffer[startIndex] and buffer[startIndex +
     * 1]
     *
     * @return
     */
    private Short twoBytesToShort(byte[] buffer, int startIndex) {
        return new Short((short)((buffer[startIndex] << 8) + (buffer[startIndex + 1] & 0x00FF)));
    }

    private void updateLookup() {
        // Update the lookup content: 
        //   Replace a new GeneratorData instance
        this.m_pInstanceContent.set(Collections.emptyList(), null);     // Clear the Lookup Instance Content
        this.m_pInstanceContent.add(this.m_pSharedObject.getCurrentGeneratorData());
    }

    /**
     * Based on the comStatus, calculate the length of data which is required
     * for the CRC
     *
     * @param comStatus
     * @return CRC length
     */
    private int crcLength(int comStatus) {
        int ret = 0;
        switch (comStatus) {
            case 1:
                ret = 3 + (ModbusConstants.SEGMENT_1_WORD_COUNT << 1);
                break;
            case 2:
                ret = 3 + (ModbusConstants.SEGMENT_2_WORD_COUNT << 1);
                break;
            case 3:
                ret = 3 + (ModbusConstants.SEGMENT_3_WORD_COUNT << 1);
                break;
            case 4:
                ret = 3 + (ModbusConstants.SEGMENT_4_WORD_COUNT << 1);
                break;
            case 5:
                ret = 3 + (ModbusConstants.SEGMENT_5_WORD_COUNT << 1);
                break;
            case 6:
                ret = 3 + (ModbusConstants.SEGMENT_6_WORD_COUNT << 1);
                break;
            case 7:
                ret = 3 + (ModbusConstants.SEGMENT_7_WORD_COUNT << 1);
                break;
            case 8:
                ret = 3 + (ModbusConstants.SEGMENT_8_WORD_COUNT << 1);
                break;
            default:
                break;
        }

        return ret;
    }
}
