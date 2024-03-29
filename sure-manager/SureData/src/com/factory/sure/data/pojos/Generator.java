package com.factory.sure.data.pojos;
// Generated Jul 18, 2013 1:19:56 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Generator generated by hbm2java
 */
public class Generator implements java.io.Serializable {

    private Integer id;
    private byte mModbusAddress;
    private Set<GeneratorData> m_pGeneratorDatas = new HashSet<GeneratorData>(0);

    public Generator() {
    }

    public Generator(byte modbusAddress) {
        this.mModbusAddress = modbusAddress;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getModbusAddress() {
        return this.mModbusAddress;
    }

    public void setModbusAddress(byte modbusAddress) {
        this.mModbusAddress = modbusAddress;
    }

    public Set<GeneratorData> getGeneratorDatas() {
        return this.m_pGeneratorDatas;
    }

    public void setGeneratorDatas(Set<GeneratorData> generatorDatas) {
        this.m_pGeneratorDatas = generatorDatas;
    }
}
