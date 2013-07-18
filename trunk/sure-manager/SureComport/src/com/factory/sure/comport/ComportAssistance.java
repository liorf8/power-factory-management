/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport;

import com.factory.sure.comport.data.Factory;
import com.factory.sure.comport.data.SharedObject;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import com.factory.sure.comport.uart.SerialPortReader;
import com.factory.sure.comport.uart.UARTSendTimer;
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

    private String m_ComPort = "COM5";  // TODO: config the comport
    //The Serial port to connect with the Master board.
    private SerialPort m_SerialPort;
    // The UART sending work. This will send data requests to the Master board
    // to get appropriate values.
    private UARTSendTimer m_UARTSendTimer;
    // Shared object between UART sender and receiver
    private SharedObject m_pSharedObject;
    // The m_pFactory data object
    private Factory m_pFactory;
    // The lookup
    private Lookup m_pLookup;
    
    // The lookup content
    private InstanceContent m_pInstanceContent;

    public ComportAssistance() {
        this.m_pFactory = new Factory();
        this.m_pSharedObject = new SharedObject();
        this.m_pInstanceContent = new InstanceContent();
        this.m_pLookup = new AbstractLookup(m_pInstanceContent);
    }

    /**
     * Initialize the UART to be ready for working
     */
    public void initializeUART() {
        m_SerialPort = new SerialPort(m_ComPort);
        try {
            m_SerialPort.openPort();
            m_SerialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR;//Prepare mask
            m_SerialPort.setEventsMask(mask);//Set mask

            //Initialize the request data sender
            m_UARTSendTimer = new UARTSendTimer(m_SerialPort, ModbusConstants.TIME_INTERVAL, this.getSharedObject(), this.m_pFactory);

            // Add the COM port event listener to process the recevied data
            // The SerialPortReader should know the comStatus of the UARTSendTimer
            // to know the segment to process.
            m_SerialPort.addEventListener(new SerialPortReader(m_SerialPort, this.getSharedObject(), this.m_pInstanceContent));//Add SerialPortEventListener
        } catch (SerialPortException ex) {
            System.out.println("SerialPortException");
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
        m_UARTSendTimer.start();
    }

    /**
     * Stop the UART work
     */
    public void stopUART() {
        m_UARTSendTimer.stop();
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
