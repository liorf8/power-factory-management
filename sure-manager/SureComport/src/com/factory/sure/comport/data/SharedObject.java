/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.data;

import com.factory.sure.comport.helper.constants.ModbusConstants;
import java.util.concurrent.locks.*;

/**
 * This class hold three GeneratorDataArrayList as Circular Buffer and one 
 * comStatus variable to share between the serial port sender and receiver
 * @author MinhLuan
 */
public class SharedObject {
    private ReentrantLock m_pLock;
    private GeneratorData m_pCurrentGeneratorData;
    private int m_ComStatus;
    
    /**
     * Initialize of of the data
     */
    public SharedObject() {
        this.m_pLock = new ReentrantLock();
        
//        // Associate some GeneratorData for the lookup
//        this.m_pCurrentGeneratorData = new GeneratorData();
//        this.m_pCurrentGeneratorData.resetAllValues();
        
        this.m_ComStatus = 0;
    }

    /**
     * @return the comStatus
     */
    public int getComStatus() {
        return m_ComStatus;
    }

    /**
     * @param comStatus the comStatus to set
     */
    public void setComStatus(int comStatus) {
        this.m_ComStatus = comStatus;
    }
    
    /**
     * Set the comStatus to the next one and return the new comStatus
     * @return the new comStatus
     */
    public int nextComStatus() {
        this.m_ComStatus ++;
        if (this.m_ComStatus > ModbusConstants.SEGMENT_NUMBER) {
            this.m_ComStatus = 1;
        }
        return this.m_ComStatus;
    }

    /**
     * @return the lock
     */
    public ReentrantLock getLock() {
        return m_pLock;
    }

    /**
     * @return the m_pCurrentGeneratorData
     */
    public GeneratorData getCurrentGeneratorData() {
        return m_pCurrentGeneratorData;
    }

    /**
     * @param m_pCurrentGeneratorData the m_pCurrentGeneratorData to set
     */
    public void setCurrentGeneratorData(GeneratorData currentGeneratorData) {
        this.m_pCurrentGeneratorData = currentGeneratorData;
    }
    
    
}
