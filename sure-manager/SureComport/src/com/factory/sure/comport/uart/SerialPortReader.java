/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.uart;

import com.factory.sure.data.pojos.GeneratorData;
import com.factory.sure.comport.data.SharedObject;
import com.factory.sure.comport.helper.ModbusCRC;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import com.factory.sure.data.FactoryDataAssistance;
import com.factory.sure.data.configuration.Configuration;
import com.factory.sure.data.exception.WrongGeneratorException;
import com.factory.sure.data.pojos.Factory;
import com.factory.sure.data.pojos.Generator;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
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

        if (Configuration.DEBUG_MODE == true) {
            Timer fakeDataTimer = new Timer();
            fakeDataTimer.schedule(new FakeGeneratorDataTask(), 0, 500);
        }

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
        generatorData.setG_Ua(twoBytesToFloat(buffer, 3));
        generatorData.setG_Ub(twoBytesToFloat(buffer, 5));
        generatorData.setG_Uc(twoBytesToFloat(buffer, 7));
        generatorData.setG_Uab(twoBytesToFloat(buffer, 9));
        generatorData.setG_Ubc(twoBytesToFloat(buffer, 11));
        generatorData.setG_Uca(twoBytesToFloat(buffer, 13));
        generatorData.setL_Ua(twoBytesToFloat(buffer, 15));
        generatorData.setL_Ub(twoBytesToFloat(buffer, 17));
        generatorData.setL_Uc(twoBytesToFloat(buffer, 19));
        generatorData.setL_Uab(twoBytesToFloat(buffer, 21));
        generatorData.setL_Ubc(twoBytesToFloat(buffer, 23));
        generatorData.setL_Uca(twoBytesToFloat(buffer, 25));
    }

    private void processSegment2(GeneratorData generatorData, byte[] buffer) {
//        generatorData.setGL_Ua(new Short((short) ((buffer[3] << 8) + buffer[4])));
//        generatorData.setGL_Ub(new Short((short) ((buffer[5] << 8) + buffer[6])));
//        generatorData.setGL_Uc(new Short((short) ((buffer[7] << 8) + buffer[8])));
        generatorData.setIa(twoBytesToFloat(buffer, 9));
        generatorData.setIb(twoBytesToFloat(buffer, 11));
        generatorData.setIc(twoBytesToFloat(buffer, 13));
        generatorData.setPa(twoBytesToFloat(buffer, 15));
        generatorData.setPb(twoBytesToFloat(buffer, 17));
        generatorData.setPc(twoBytesToFloat(buffer, 19));
        generatorData.setQa(twoBytesToFloat(buffer, 21));
        generatorData.setQb(twoBytesToFloat(buffer, 23));
        generatorData.setQc(twoBytesToFloat(buffer, 25));
    }

    private void processSegment3(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSa(twoBytesToFloat(buffer, 3));
        generatorData.setSb(twoBytesToFloat(buffer, 5));
        generatorData.setSc(twoBytesToFloat(buffer, 7));
        generatorData.setPFa(twoBytesToFloat(buffer, 9));
        generatorData.setPFb(twoBytesToFloat(buffer, 11));
        generatorData.setPFc(twoBytesToFloat(buffer, 13));
        generatorData.setP3Phases(twoBytesToFloat(buffer, 15));
        generatorData.setQ3Phases(twoBytesToFloat(buffer, 17));
        generatorData.setS3Phases(twoBytesToFloat(buffer, 19));
        generatorData.setPF3Phases(twoBytesToFloat(buffer, 21));
        generatorData.setG_RealFreq(twoBytesToFloat(buffer, 23));
        generatorData.setL_RealFreq(twoBytesToFloat(buffer, 25));
    }

    private void processSegment4(GeneratorData generatorData, byte[] buffer) {
        generatorData.setI0(twoBytesToFloat(buffer, 3));
        generatorData.setPowerEnergy(twoBytesToFloat(buffer, 5));
    }

    private void processSegment5(GeneratorData generatorData, byte[] buffer) {
        generatorData.setSpeedGuardStatus(twoBytesToFloat(buffer, 3));
        generatorData.setRPM(twoBytesToFloat(buffer, 5));
    }

    private void processSegment6(GeneratorData generatorData, byte[] buffer) {
        generatorData.setTemperature1(twoBytesToFloat(buffer, 3));
        generatorData.setTemperature2(twoBytesToFloat(buffer, 5));
        generatorData.setTemperature3(twoBytesToFloat(buffer, 7));
        generatorData.setTemperature4(twoBytesToFloat(buffer, 9));
        generatorData.setTemperature5(twoBytesToFloat(buffer, 11));
        generatorData.setTemperature6(twoBytesToFloat(buffer, 13));
        generatorData.setTemperature7(twoBytesToFloat(buffer, 15));
        generatorData.setTemperature8(twoBytesToFloat(buffer, 17));
        generatorData.setTemperature9(twoBytesToFloat(buffer, 19));
        generatorData.setTemperature10(twoBytesToFloat(buffer, 21));
        generatorData.setTemperature11(twoBytesToFloat(buffer, 23));
        generatorData.setTemperature12(twoBytesToFloat(buffer, 25));
    }

    private void processSegment7(GeneratorData generatorData, byte[] buffer) {
        generatorData.setPress1(twoBytesToFloat(buffer, 3));
        generatorData.setPress2(twoBytesToFloat(buffer, 5));
        generatorData.setPress3(twoBytesToFloat(buffer, 7));
        generatorData.setPress4(twoBytesToFloat(buffer, 9));
        generatorData.setContactGroup1(twoBytesToFloat(buffer, 11));
        generatorData.setContactGroup2(twoBytesToFloat(buffer, 13));
        generatorData.setContactGroup3(twoBytesToFloat(buffer, 15));
        generatorData.setContactGroup4(twoBytesToFloat(buffer, 17));
    }

    private void processSegment8(GeneratorData generatorData, byte[] buffer) {
        generatorData.setWaterOpenAngle(twoBytesToFloat(buffer, 3));
        generatorData.setFuelLevel1(twoBytesToFloat(buffer, 5));
        generatorData.setFuelLevel2(twoBytesToFloat(buffer, 7));
        generatorData.setValvePlace(twoBytesToFloat(buffer, 9));
    }

    /**
     * Convert two continuous bytes: buffer[startIndex] and buffer[startIndex +
     * 1]
     *
     * @return
     */
    private Float twoBytesToFloat(byte[] buffer, int startIndex) {
        return new Float((float) ((buffer[startIndex] << 8) + (buffer[startIndex + 1] & 0x00FF)));
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

    private class FakeGeneratorDataTask extends TimerTask {
        List<Generator> m_pGeneratorList;
        private int tmpCounter = 0;

        public FakeGeneratorDataTask() {
            m_pGeneratorList = new LinkedList<Generator>();

            Set<Byte> modbusSet = new LinkedHashSet<Byte>(ModbusConstants.NUM_OF_GENERATORS);
            modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_1_MODBUS_ADDRESS));
            modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_2_MODBUS_ADDRESS));
            modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_3_MODBUS_ADDRESS));
            FactoryDataAssistance factoryDataAssistance = new FactoryDataAssistance();
            try {
                Factory factory = factoryDataAssistance.initializeFactoryData(modbusSet);
                m_pGeneratorList = factory.getGenerators();
            } catch (WrongGeneratorException exception) {
            }
        }

        @Override
        public void run() {
            System.out.println("FakeGeneratorDataTask run...");
            m_pInstanceContent.set(Collections.emptyList(), null);
            GeneratorData tmpGeneratorData = new GeneratorData(m_pGeneratorList.get(tmpCounter));
            tmpGeneratorData.setRandomValues();
            m_pInstanceContent.add(tmpGeneratorData);

            tmpCounter++;
            if (tmpCounter >= ModbusConstants.NUM_OF_GENERATORS) {
                tmpCounter = 0;
            }
        }
    }
}
