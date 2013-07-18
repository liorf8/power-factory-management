package com.factory.sure.comport.uart;

import com.factory.sure.comport.data.Factory;
import com.factory.sure.comport.data.Generator;
import com.factory.sure.comport.data.SharedObject;
import com.factory.sure.comport.helper.ModbusCRC;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

public class UARTSendTimer {

    private long m_timeInterval;
    private SerialPort m_pSerialPort;
    // Shared object between UART sender and receiver
    private SharedObject m_pSharedObject;
    // The factory containing a list of all generators
    private Factory m_pFactory;
    private byte m_memormyAddressL;
    private byte m_memorymAddressH;
    private byte m_modBusFunctionCode;
    private byte m_comLengthL;
    private byte m_comLengthH;
    // The timer to request data
    private Timer m_pTimer;

    public UARTSendTimer(SerialPort serialPort, long timeInterval, SharedObject sharedObject, Factory factory) {
        this.m_pSharedObject = sharedObject;

        this.m_pSerialPort = serialPort;
        m_timeInterval = timeInterval;
        m_pTimer = new Timer();

        this.m_pFactory = factory;
    }

    public void start() {
        m_pTimer.schedule(new RequestDataTask(this.m_pSharedObject, this.m_pFactory), m_timeInterval, m_timeInterval);
    }

    public void stop() {
        m_pTimer.cancel();
    }

    private class RequestDataTask extends TimerTask {

        private byte[] m_pSendBytes = new byte[ModbusConstants.MOBUS_REQUEST_MESSAGE_LENGTH];
        // Shared object between UART sender and receiver
        private SharedObject m_pSharedObject;
        // The factory containing a list of all generators
        private Factory m_pFactory;
        // The index of the current generator
        private int m_CurrentGeneratorIndex;

        public RequestDataTask(SharedObject sharedObject, Factory factory) {
            super();
            this.m_pSharedObject = sharedObject;
            this.m_pFactory = factory;
            this.m_CurrentGeneratorIndex = -1;
        }

        @Override
        public void run() {
            if (m_pSerialPort.isOpened()) {
                try {
                    ReentrantLock lock = this.m_pSharedObject.getLock();
                    lock.lock();
                    int comStatus;
                    try {
                        comStatus = this.m_pSharedObject.nextComStatus();
                        Generator currentGenerator = this.getCurrentGenerator();
                        if (comStatus == 1) {   // It means that the generator should be change to the next one
                            this.nextGeneratorIndex();

                            // Update new currentGenerator
                            this.m_pSharedObject.setCurrentGeneratorData(currentGenerator.getNextGeneratorData());
                            System.out.println("Update new currentGenerator to " + currentGenerator.getModbusAddress());
                            // System.out.println("Change to the generator index: " + this.m_CurrentGeneratorIndex);
                        }
                        m_pSendBytes[0] = currentGenerator.getModbusAddress();

                    } finally {
                        lock.unlock();
                    }
                    switch (comStatus) {
                        case 1: //Ask for segment 1
                            configureModbus(ModbusConstants.SEGMENT_1_ADDRESS, ModbusConstants.SEGMENT_1_WORD_COUNT);
                            break;
                        case 2: //Ask for segment 2
                            configureModbus(ModbusConstants.SEGMENT_2_ADDRESS, ModbusConstants.SEGMENT_2_WORD_COUNT);
                            break;
                        case 3: //Ask for segment 3
                            configureModbus(ModbusConstants.SEGMENT_3_ADDRESS, ModbusConstants.SEGMENT_3_WORD_COUNT);
                            break;
                        case 4: //Ask for segment 4
                            configureModbus(ModbusConstants.SEGMENT_4_ADDRESS, ModbusConstants.SEGMENT_4_WORD_COUNT);
                            break;
                        case 5: //Ask for segment 5
                            configureModbus(ModbusConstants.SEGMENT_5_ADDRESS, ModbusConstants.SEGMENT_5_WORD_COUNT);
                            break;
                        case 6: //Ask for segment 5
                            configureModbus(ModbusConstants.SEGMENT_6_ADDRESS, ModbusConstants.SEGMENT_6_WORD_COUNT);
                            break;
                        case 7: //Ask for segment 5
                            configureModbus(ModbusConstants.SEGMENT_7_ADDRESS, ModbusConstants.SEGMENT_7_WORD_COUNT);
                            break;
                        case 8: //Ask for segment 5
                            configureModbus(ModbusConstants.SEGMENT_8_ADDRESS, ModbusConstants.SEGMENT_8_WORD_COUNT);
                            break;
                        default:
                            break;
                    }
                    m_pSerialPort.readBytes();  //Clear the buffer
                    m_pSerialPort.writeBytes(m_pSendBytes);
//                    System.out.println("Sent request for segment " + this.m_pSharedObject.getComStatus());
                } catch (SerialPortException ex) {
                    // TODO: check the Logger here
                    Logger.getLogger(UARTSendTimer.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Serialport exception");
                }
            }
        }

        private void configureModbus(int memoryAddress, int comLength) {
            // Modbus function code to request for data
            m_modBusFunctionCode = ModbusConstants.MODBUS_REQUEST_FUNCTION_CODE;

            //The data length to request
            m_comLengthH = (byte) ((comLength & 0x0000ff00) >> 8);
            m_comLengthL = (byte) (comLength & 0x000000ff);

            //The data address to request
            m_memorymAddressH = (byte) ((memoryAddress & 0x0000ff00) >> 8);
            m_memormyAddressL = (byte) (memoryAddress & 0x000000ff);

            m_pSendBytes[1] = m_modBusFunctionCode;
            m_pSendBytes[2] = m_memorymAddressH;
            m_pSendBytes[3] = m_memormyAddressL;
            m_pSendBytes[4] = m_comLengthH;
            m_pSendBytes[5] = m_comLengthL;

            //The CRC in the Master board use LSB
            int crc = ModbusCRC.doCRC16(m_pSendBytes, 0, 6);
            m_pSendBytes[6] = (byte) (crc & 0x000000ff);
            m_pSendBytes[7] = (byte) ((crc & 0x0000ff00) >> 8);
        }

        /**
         * Calculate the next generator index
         *
         * @return the next generator index
         */
        private int nextGeneratorIndex() {
            this.m_CurrentGeneratorIndex++;
            if (this.m_CurrentGeneratorIndex >= ModbusConstants.NUM_OF_GENERATORS) {
                this.m_CurrentGeneratorIndex = 0;
            }

            return this.m_CurrentGeneratorIndex;
        }

        /**
         * Get the current generator depending on the generator index
         *
         * @return the current generator object
         */
        private Generator getCurrentGenerator() {
            if (this.m_CurrentGeneratorIndex <= 0) {
                this.m_CurrentGeneratorIndex = this.m_pFactory.getGenerators().size() -1;
            }
            return this.m_pFactory.getGenerators().get(this.m_CurrentGeneratorIndex);
        }
    } // End class RequestData

    /**
     * @return the m_timeInterval
     */
    public long getTimeInterval() {
        return m_timeInterval;
    }

    /**
     * This function modify the time interval. Stop the current timer and start
     * it again with the new time interval.
     *
     * @param timeInterval the m_timeInterval to set
     */
    public void setTimeInterval(long timeInterval) {
        this.m_timeInterval = timeInterval;
        this.stop();
        this.start();
    }
}
