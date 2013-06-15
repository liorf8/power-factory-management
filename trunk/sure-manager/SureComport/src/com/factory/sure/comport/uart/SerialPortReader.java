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
        generatorData.setG_Ua(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setG_Ub(new Short((short) ((buffer[5] << 8) + buffer[6])));
        generatorData.setG_Uc(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setG_Uab(new Short((short) ((buffer[9] << 8) + buffer[10])));
        generatorData.setG_Ubc(new Short((short) ((buffer[11] << 8) + buffer[12])));
        generatorData.setG_Uca(new Short((short) ((buffer[13] << 8) + buffer[14])));
        generatorData.setL_Ua(new Short((short) ((buffer[15] << 8) + buffer[16])));
        generatorData.setL_Ub(new Short((short) ((buffer[17] << 8) + buffer[18])));
        generatorData.setL_Uc(new Short((short) ((buffer[19] << 8) + buffer[20])));
        generatorData.setL_Uab(new Short((short) ((buffer[21] << 8) + buffer[22])));
        generatorData.setL_Ubc(new Short((short) ((buffer[23] << 8) + buffer[24])));
        generatorData.setL_Uca(new Short((short) ((buffer[25] << 8) + buffer[26])));
    }

    private void processSegment2(GeneratorData generatorData, byte[] buffer) {
//        generatorData.setGL_Ua(new Short((short) ((buffer[3] << 8) + buffer[4])));
//        generatorData.setGL_Ub(new Short((short) ((buffer[5] << 8) + buffer[6])));
//        generatorData.setGL_Uc(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setIa(new Short((short) ((buffer[9] << 8) + buffer[10])));
        generatorData.setIb(new Short((short) ((buffer[11] << 8) + buffer[12])));
        generatorData.setIc(new Short((short) ((buffer[13] << 8) + buffer[14])));
        generatorData.setPa(new Short((short) ((buffer[15] << 8) + buffer[16])));
        generatorData.setPb(new Short((short) ((buffer[17] << 8) + buffer[18])));
        generatorData.setPc(new Short((short) ((buffer[19] << 8) + buffer[20])));
        generatorData.setQa(new Short((short) ((buffer[21] << 8) + buffer[22])));
        generatorData.setQb(new Short((short) ((buffer[23] << 8) + buffer[24])));
        generatorData.setQc(new Short((short) ((buffer[25] << 8) + buffer[26])));
    }

    private void processSegment3(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSa(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setSb(new Short((short) ((buffer[5] << 8) + buffer[6])));
        generatorData.setSc(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setPFa(new Short((short) ((buffer[9] << 8) + buffer[10])));
        generatorData.setPFb(new Short((short) ((buffer[11] << 8) + buffer[12])));
        generatorData.setPFc(new Short((short) ((buffer[13] << 8) + buffer[14])));
        generatorData.setP3Phases(new Short((short) ((buffer[15] << 8) + buffer[16])));
        generatorData.setQ3Phases(new Short((short) ((buffer[17] << 8) + buffer[18])));
        generatorData.setS3Phases(new Short((short) ((buffer[19] << 8) + buffer[20])));
        generatorData.setPF3Phases(new Short((short) ((buffer[21] << 8) + buffer[22])));
        generatorData.setG_RealFreq(new Short((short) ((buffer[23] << 8) + buffer[24])));
        generatorData.setL_RealFreq(new Short((short) ((buffer[25] << 8) + buffer[26])));
    }

    private void processSegment4(GeneratorData generatorData, byte[] buffer) {
        generatorData.setI0(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setPowerEnergy(new Short((short) ((buffer[5] << 8) + buffer[6])));
    }

    private void processSegment5(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSpeedGuardStatus(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setRPM(new Short((short) ((buffer[5] << 8) + buffer[6])));
    }

    private void processSegment6(GeneratorData generatorData, byte[] buffer) {
        generatorData.setTemperature1(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setTemperature2(new Short((short) ((buffer[5] << 8) + buffer[6])));
        generatorData.setTemperature3(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setTemperature4(new Short((short) ((buffer[9] << 8) + buffer[10])));
        generatorData.setTemperature5(new Short((short) ((buffer[11] << 8) + buffer[12])));
        generatorData.setTemperature6(new Short((short) ((buffer[13] << 8) + buffer[14])));
        generatorData.setTemperature7(new Short((short) ((buffer[15] << 8) + buffer[16])));
        generatorData.setTemperature8(new Short((short) ((buffer[17] << 8) + buffer[18])));
        generatorData.setTemperature9(new Short((short) ((buffer[19] << 8) + buffer[20])));
        generatorData.setTemperature10(new Short((short) ((buffer[21] << 8) + buffer[22])));
        generatorData.setTemperature11(new Short((short) ((buffer[23] << 8) + buffer[24])));
        generatorData.setTemperature12(new Short((short) ((buffer[25] << 8) + buffer[26])));
    }

    private void processSegment7(GeneratorData generatorData, byte[] buffer) {
        generatorData.setPress1(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setPress2(new Short((short) ((buffer[5] << 8) + buffer[6])));
        generatorData.setPress3(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setPress4(new Short((short) ((buffer[9] << 8) + buffer[10])));
        generatorData.setContactGroup1(new Short((short) ((buffer[11] << 8) + buffer[12])));
        generatorData.setContactGroup2(new Short((short) ((buffer[13] << 8) + buffer[14])));
        generatorData.setContactGroup3(new Short((short) ((buffer[15] << 8) + buffer[16])));
        generatorData.setContactGroup4(new Short((short) ((buffer[17] << 8) + buffer[18])));
    }

    private void processSegment8(GeneratorData generatorData, byte[] buffer) {
        generatorData.setWaterOpenAngle(new Short((short) ((buffer[3] << 8) + buffer[4])));
        generatorData.setFuelLevel1(new Short((short) ((buffer[5] << 8) + buffer[6])));
        generatorData.setFuelLevel2(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setValvePlace(new Short((short) ((buffer[9] << 8) + buffer[10])));
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
