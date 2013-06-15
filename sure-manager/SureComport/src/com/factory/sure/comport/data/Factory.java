/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.data;

import com.factory.sure.comport.helper.constants.ModbusConstants;
import java.util.ArrayList;

/**
 *
 * @author MinhLuan
 */
public class Factory {

    private ArrayList<Generator> m_pGenerators;

    public Factory() {
        this.m_pGenerators = new ArrayList<Generator>(ModbusConstants.NUM_OF_GENERATORS);

        // Initialize all of the generator object
        // TODO: fix this to read from a configuration file
        Generator gen1 = new Generator(ModbusConstants.GENERATOR_1_MODBUS_ADDRESS);
//        Generator gen2 = new Generator(ModbusConstants.GENERATOR_2_MODBUS_ADDRESS);
//        Generator gen3  = new Generator(ModbusConstants.GENERATOR_3_MODBUS_ADDRESS);
//        Generator gen4 = new Generator(ModbusConstants.GENERATOR_4_MODBUS_ADDRESS);
        this.m_pGenerators.add(gen1);
//        this.m_pGenerators.add(gen2);
//        this.m_pGenerators.add(gen3);
//        this.m_pGenerators.add(gen4);
    }

    /**
     * @return the m_pGenerators
     */
    public ArrayList<Generator> getGenerators() {
        return m_pGenerators;
    }
}
