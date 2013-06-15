/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.helper;

import com.factory.sure.comport.data.GeneratorData;
import java.util.*;

/**
 * If you use this code, please retain this comment block.
 *
 * @author Isak du Preez isak at du-preez dot com www.du-preez.com
 */
public class GeneratorDataArrayList {

    private byte m_ModbusAddress;
    private ArrayList<GeneratorData> m_pArrayList;
    private int m_Capacity;
    private int m_Head;
    private int m_Tail;
    private int m_Size;

    public GeneratorDataArrayList(int capacity, byte modbusAddress) {
        this.m_Capacity = capacity;
        this.m_pArrayList = new ArrayList<GeneratorData>(capacity);
        this.m_Size = 0;
        this.m_Head = 0;
        this.m_Tail = 0;
        this.m_ModbusAddress = modbusAddress;

        for (int i = 0; i < capacity; i++) {
            this.m_pArrayList.add(new GeneratorData(this.m_ModbusAddress));
        }
    }

    /**
     *
     * @return the next GeneratorData in the pool after reset all values
     */
    public GeneratorData next() {
        GeneratorData generatorData = this.m_pArrayList.get(this.m_Tail);

        // Clear all of the value to null
        generatorData.resetAllValues();

        this.m_Size++;
        if (this.m_Size >= this.m_Capacity) {
            // Has been full size
            this.m_Size = this.m_Capacity - 1;

            this.m_Tail = this.m_Head;

            this.m_Head++;
            if (this.m_Head >= this.m_Capacity) {
                this.m_Head = 0;
            }
        } else {
            // Not full size
            this.m_Tail++;
        }

        return generatorData;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return m_Size;
    }
}
