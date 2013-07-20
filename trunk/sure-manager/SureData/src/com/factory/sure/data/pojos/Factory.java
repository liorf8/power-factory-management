package com.factory.sure.data.pojos;

import java.util.ArrayList;
import java.util.Set;

public class Factory {
    private ArrayList<Generator> m_pGenerators;

    public Factory(Set<Byte> modbusAddressSet) {
        // Asume that modbusAddress != null
        this.m_pGenerators = new ArrayList<Generator>(modbusAddressSet.size());
        for (Byte b : modbusAddressSet) {
            Generator gen = new Generator(b.byteValue());
            this.m_pGenerators.add(gen);
        }
    }

    /**
     * @return the m_pGenerators
     */
    public ArrayList<Generator> getGenerators() {
        return m_pGenerators;
    }
}
