package com.factory.sure.data.pojos;

import java.util.List;

public class Factory {
    private List<Generator> m_pGenerators;

    public Factory(List<Generator> generatorList) {
        this.m_pGenerators = generatorList;
    }

    /**
     * @return the m_pGenerators
     */
    public List<Generator> getGenerators() {
        return m_pGenerators;
    }
}
