/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport;

import com.factory.sure.data.pojos.Factory;
import com.factory.sure.comport.data.SharedObject;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import com.factory.sure.comport.uart.SerialPortReader;
import com.factory.sure.comport.uart.UARTSendTimer;
import com.factory.sure.data.FactoryDataAssistance;
import com.factory.sure.data.configuration.Configuration;
import com.factory.sure.data.exception.WrongGeneratorException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author MinhLuan Firstly set the comport. Secondly, initialize. Thirdly,
 * start the UART. At last, stop the UART.
 */
@ServiceProvider(service = ComportAssistance.class)
public class ComportAssistance implements Lookup.Provider {
    //The Com port number, represented as a String value

    private String m_ComPort = "";
    //The Serial port to connect with the Master board.
    private SerialPort m_SerialPort;
    // The UART sending work. This will send data requests to the Master board
    // to get appropriate values.
    private SerialPortReader m_pSerialPortReader;
    private UARTSendTimer m_pUARTSendTimer;
    // Shared object between UART sender and receiver
    private SharedObject m_pSharedObject;
    // The m_pFactory data object
    private Factory m_pFactory = null;
    // The lookup
    private Lookup m_pLookup;
    // The lookup content
    private InstanceContent m_pInstanceContent;
    // The FactoryDataAssistance
    private FactoryDataAssistance m_pFactoryDataAssistance = null;

    public ComportAssistance() throws WrongGeneratorException {
        Set<Byte> modbusSet = new LinkedHashSet<Byte>(ModbusConstants.NUM_OF_GENERATORS);
        modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_1_MODBUS_ADDRESS));
        modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_2_MODBUS_ADDRESS));
        modbusSet.add(Byte.valueOf(ModbusConstants.GENERATOR_3_MODBUS_ADDRESS));

        Collection<? extends FactoryDataAssistance> allFactoryDataAssistances = Lookup.getDefault().lookupAll(FactoryDataAssistance.class);
        for (FactoryDataAssistance factoryDataAssistance : allFactoryDataAssistances) {
            m_pFactoryDataAssistance = factoryDataAssistance;
            break;
        }
        if (m_pFactoryDataAssistance != null) {
            m_pFactory = m_pFactoryDataAssistance.initializeFactoryData(modbusSet);
            if (m_pFactory == null) {
                System.err.println("ComportAssistance ComportAssistance: m_pFactory is null");
            }
        } else {
            System.err.println("ComportAssistance ComportAssistance: m_pFactoryDataAssistance is null");
        }

        this.m_pSharedObject = new SharedObject();
        this.m_pInstanceContent = new InstanceContent();
        this.m_pLookup = new AbstractLookup(m_pInstanceContent);
    }

    /**
     * Initialize the UART to be ready for working
     */
    public void initializeUART() {
        if (Configuration.DEBUG_MODE == false) {
            m_SerialPort = new SerialPort(m_ComPort);
            try {
                m_SerialPort.openPort();
                m_SerialPort.setParams(9600, 8, 1, 0);//Set params
                int mask = SerialPort.MASK_RXCHAR;//Prepare mask
                m_SerialPort.setEventsMask(mask);//Set mask

                //Initialize the request data sender
                m_pUARTSendTimer = new UARTSendTimer(m_SerialPort, ModbusConstants.TIME_INTERVAL, this.getSharedObject(), this.m_pFactory);

                // Add the COM port event listener to process the recevied data
                // The SerialPortReader should know the comStatus of the UARTSendTimer
                // to know the segment to process.
                m_pSerialPortReader = new SerialPortReader(m_SerialPort, this.getSharedObject(), this.m_pInstanceContent);
                m_SerialPort.addEventListener(m_pSerialPortReader);//Add SerialPortEventListener
            } catch (SerialPortException ex) {
                System.out.println("SerialPortException");
            }
        } else {
            // DEBUG_MODE = true
            m_pSerialPortReader = new SerialPortReader(m_SerialPort, this.getSharedObject(), this.m_pInstanceContent);
        }
    }

    /**
     * Initialize the UART to be ready for working
     *
     * @param comport The comport to be used
     */
    public void initializeUART(String comport) {
        this.setComPort(comport);
        this.initializeUART();
    }

    /**
     * Start the UART work
     */
    public void startUART() {
        m_pUARTSendTimer.start();
    }

    /**
     * Stop the UART work
     */
    public void stopUART() {
        m_pUARTSendTimer.stop();
    }

    /**
     * @return the comPort
     */
    public String getComPort() {
        return m_ComPort;
    }

    /**
     * @param comPort the comPort to set
     */
    public void setComPort(String comPort) {
        this.m_ComPort = comPort;
    }

    /**
     * @return the m_pSharedObject
     */
    public SharedObject getSharedObject() {
        return m_pSharedObject;
    }

    @Override
    public Lookup getLookup() {
        return this.m_pLookup;
    }
}
