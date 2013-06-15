/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.data;

import com.factory.sure.comport.helper.GeneratorDataArrayList;
import com.factory.sure.comport.helper.constants.ModbusConstants;

/**
 *
 * @author MinhLuan
 */
public class Generator {
     // The generator circular buffer
    private GeneratorDataArrayList m_pInstantHistoryBuffer;
    
    // The generator Modbus address
    private byte m_ModbusAddress;
    
    public Generator(byte modbusAddress) {
        this.m_ModbusAddress = modbusAddress;
        this.m_pInstantHistoryBuffer = new GeneratorDataArrayList(ModbusConstants.HISTORY_CAPACITY, this.m_ModbusAddress);
    }

    /**
     * @return the modbusAddress
     */
    public byte getModbusAddress() {
        return m_ModbusAddress;
    }

    /**
     * @return the m_pInstantHistoryBuffer
     */
    public GeneratorDataArrayList getInstantHistoryBuffer() {
        return m_pInstantHistoryBuffer;
    }

    /**
     * 
     * @return the next generator data from the Instant history buffer
     */
    public GeneratorData getNextGeneratorData() {
        return this.m_pInstantHistoryBuffer.next();
    }
}
