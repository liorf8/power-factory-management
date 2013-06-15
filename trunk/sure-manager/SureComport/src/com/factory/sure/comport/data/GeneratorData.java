/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.comport.data;

/**
 *
 * @author MinhLuan
 */
public class GeneratorData {

    private byte m_ModbusAddress;
    // Dien ap may phat cac pha A, B, C
    private Short m_G_Ua;             // Address: 60
    private Short m_G_Ub;             // Address: 61
    private Short m_G_Uc;             // Address: 62
    // Dien ap may phat cac day
    private Short m_G_Uab;            // Address: 63
    private Short m_G_Ubc;            // Address: 64
    private Short m_G_Uca;            // Address: 65
    // Dien ap luoi cac pha A, B, C
    private Short m_L_Ua;          // Address: 66    
    private Short m_L_Ub;          // Address: 67
    private Short m_L_Uc;          // Address: 68
    // Dien ap day phia luoi
    private Short m_L_Uab;         // Address: 69
    private Short m_L_Ubc;         // Address: 70
    private Short m_L_Uca;         // Address: 71
    // Diep ap chenh lech
    // TODO: tempororal ignore these numbers
//    private Short GL_Ua;       // Address: 72
//    private Short GL_Ub;       // Address: 73
//    private Short GL_Uc;       // Address: 74
    // Cuong do dong dien cac pha A, B, C
    private Short m_Ia;            // Address: 75
    private Short m_Ib;            // Address: 76
    private Short m_Ic;            // Address: 77
    // Cong suat cac pha
    private Short m_Pa;            // Address: 78
    private Short m_Pb;            // Address: 79
    private Short m_Pc;            // Address: 80
    // Cong suat phan khang cac pha
    private Short m_Qa;            // Address: 81
    private Short m_Qb;            // Address: 82
    private Short m_Qc;            // Address: 83
    // Cong suat bieu kien cac pha
    private Short m_Sa;            // Address: 84
    private Short m_Sb;            // Address: 85
    private Short m_Sc;            // Address: 86
    // Cos(phi) (power factor) cac pha
    private Short m_PFa;           // Address: 87
    private Short m_PFb;           // Address: 88
    private Short m_PFc;           // Address: 89
    // Cac thong so 3 pha
    private Short m_P3Phases;      // Address: 90
    private Short m_Q3Phases;      // Address: 91
    private Short m_S3Phases;      // Address: 92
    private Short m_PF3Phases;        // Address: 93
    // Cac tan so dao dong
    private Short m_G_RealFreq;    // Address: 94. Value: 50Hz -> 5000Hz
    private Short m_L_RealFreq;    // Address: 95. Value: 50Hz -> 5000Hz
    private Short m_I0;            // Address: 96
    private Short m_PowerEnergy;   // Address: 97
    private Short m_SpeedGuardStatus; // Address: 111
    private Short m_RPM;           // Address: 112
    // Nhiet do 12 vi tri
    private Short m_Temperature1;  // Address: 132
    private Short m_Temperature2;  // Address: 133
    private Short m_Temperature3;  // Address: 134
    private Short m_Temperature4;  // Address: 135
    private Short m_Temperature5;  // Address: 136
    private Short m_Temperature6;  // Address: 137
    private Short m_Temperature7;  // Address: 138
    private Short m_Temperature8;  // Address: 139
    private Short m_Temperature9;  // Address: 140
    private Short m_Temperature10; // Address: 141
    private Short m_Temperature11; // Address: 142
    private Short m_Temperature12; // Address: 143
    // Ap suat 12 vi tri
    private Short m_Press1;        // Address: 144
    private Short m_Press2;        // Address: 145
    private Short m_Press3;        // Address: 146
    private Short m_Press4;        // Address: 147
    // Trang thai cac contact
    private Short m_ContactGroup1;    // Address: 148
    private Short m_ContactGroup2;    // Address: 149
    private Short m_ContactGroup3;    // Address: 150
    private Short m_ContactGroup4;    // Address: 151
    // Mot so thong so khac
    private Short m_WaterOpenAngle;   // Address: 156
    private Short m_FuelLevel1;       // Address: 157
    private Short m_FuelLevel2;       // Address: 158
    private Short m_ValvePlace;       // Address: 159

    /**
     * Constructor
     */
    public GeneratorData(byte modbusAddress) {
        this.m_ModbusAddress = modbusAddress;
    }

    /**
     * @return the modbusAddress
     */
    public byte getModbusAddress() {
        return m_ModbusAddress;
    }

    /**
     * @return the G_Ua
     */
    public Short getG_Ua() {
        return m_G_Ua;
    }

    /**
     * @param G_Ua the G_Ua to set
     */
    public void setG_Ua(Short G_Ua) {
        this.m_G_Ua = G_Ua;
    }

    /**
     * @return the G_Ub
     */
    public Short getG_Ub() {
        return m_G_Ub;
    }

    /**
     * @param G_Ub the G_Ub to set
     */
    public void setG_Ub(Short G_Ub) {
        this.m_G_Ub = G_Ub;
    }

    /**
     * @return the G_Uc
     */
    public Short getG_Uc() {
        return m_G_Uc;
    }

    /**
     * @param G_Uc the G_Uc to set
     */
    public void setG_Uc(Short G_Uc) {
        this.m_G_Uc = G_Uc;
    }

    /**
     * @return the G_Uab
     */
    public Short getG_Uab() {
        return m_G_Uab;
    }

    /**
     * @param G_Uab the G_Uab to set
     */
    public void setG_Uab(Short G_Uab) {
        this.m_G_Uab = G_Uab;
    }

    /**
     * @return the G_Ubc
     */
    public Short getG_Ubc() {
        return m_G_Ubc;
    }

    /**
     * @param G_Ubc the G_Ubc to set
     */
    public void setG_Ubc(Short G_Ubc) {
        this.m_G_Ubc = G_Ubc;
    }

    /**
     * @return the G_Uca
     */
    public Short getG_Uca() {
        return m_G_Uca;
    }

    /**
     * @param G_Uca the G_Uca to set
     */
    public void setG_Uca(Short G_Uca) {
        this.m_G_Uca = G_Uca;
    }

    /**
     * @return the L_Ua
     */
    public Short getL_Ua() {
        return m_L_Ua;
    }

    /**
     * @param L_Ua the L_Ua to set
     */
    public void setL_Ua(Short L_Ua) {
        this.m_L_Ua = L_Ua;
    }

    /**
     * @return the L_Ub
     */
    public Short getL_Ub() {
        return m_L_Ub;
    }

    /**
     * @param L_Ub the L_Ub to set
     */
    public void setL_Ub(Short L_Ub) {
        this.m_L_Ub = L_Ub;
    }

    /**
     * @return the L_Uc
     */
    public Short getL_Uc() {
        return m_L_Uc;
    }

    /**
     * @param L_Uc the L_Uc to set
     */
    public void setL_Uc(Short L_Uc) {
        this.m_L_Uc = L_Uc;
    }

    /**
     * @return the L_Uab
     */
    public Short getL_Uab() {
        return m_L_Uab;
    }

    /**
     * @param L_Uab the L_Uab to set
     */
    public void setL_Uab(Short L_Uab) {
        this.m_L_Uab = L_Uab;
    }

    /**
     * @return the L_Ubc
     */
    public Short getL_Ubc() {
        return m_L_Ubc;
    }

    /**
     * @param L_Ubc the L_Ubc to set
     */
    public void setL_Ubc(Short L_Ubc) {
        this.m_L_Ubc = L_Ubc;
    }

    /**
     * @return the L_Uca
     */
    public Short getL_Uca() {
        return m_L_Uca;
    }

    /**
     * @param L_Uca the L_Uca to set
     */
    public void setL_Uca(Short L_Uca) {
        this.m_L_Uca = L_Uca;
    }

    /**
     * @return the Ia
     */
    public Short getIa() {
        return m_Ia;
    }

    /**
     * @param Ia the Ia to set
     */
    public void setIa(Short Ia) {
        this.m_Ia = Ia;
    }

    /**
     * @return the Ib
     */
    public Short getIb() {
        return m_Ib;
    }

    /**
     * @param Ib the Ib to set
     */
    public void setIb(Short Ib) {
        this.m_Ib = Ib;
    }

    /**
     * @return the Ic
     */
    public Short getIc() {
        return m_Ic;
    }

    /**
     * @param Ic the Ic to set
     */
    public void setIc(Short Ic) {
        this.m_Ic = Ic;
    }

    /**
     * @return the Pa
     */
    public Short getPa() {
        return m_Pa;
    }

    /**
     * @param Pa the Pa to set
     */
    public void setPa(Short Pa) {
        this.m_Pa = Pa;
    }

    /**
     * @return the Pb
     */
    public Short getPb() {
        return m_Pb;
    }

    /**
     * @param Pb the Pb to set
     */
    public void setPb(Short Pb) {
        this.m_Pb = Pb;
    }

    /**
     * @return the Pc
     */
    public Short getPc() {
        return m_Pc;
    }

    /**
     * @param Pc the Pc to set
     */
    public void setPc(Short Pc) {
        this.m_Pc = Pc;
    }

    /**
     * @return the Qa
     */
    public Short getQa() {
        return m_Qa;
    }

    /**
     * @param Qa the Qa to set
     */
    public void setQa(Short Qa) {
        this.m_Qa = Qa;
    }

    /**
     * @return the Qb
     */
    public Short getQb() {
        return m_Qb;
    }

    /**
     * @param Qb the Qb to set
     */
    public void setQb(Short Qb) {
        this.m_Qb = Qb;
    }

    /**
     * @return the Qc
     */
    public Short getQc() {
        return m_Qc;
    }

    /**
     * @param Qc the Qc to set
     */
    public void setQc(Short Qc) {
        this.m_Qc = Qc;
    }

    /**
     * @return the Sa
     */
    public Short getSa() {
        return m_Sa;
    }

    /**
     * @param Sa the Sa to set
     */
    public void setSa(Short Sa) {
        this.m_Sa = Sa;
    }

    /**
     * @return the Sb
     */
    public Short getSb() {
        return m_Sb;
    }

    /**
     * @param Sb the Sb to set
     */
    public void setSb(Short Sb) {
        this.m_Sb = Sb;
    }

    /**
     * @return the Sc
     */
    public Short getSc() {
        return m_Sc;
    }

    /**
     * @param Sc the Sc to set
     */
    public void setSc(Short Sc) {
        this.m_Sc = Sc;
    }

    /**
     * @return the PFa
     */
    public Short getPFa() {
        return m_PFa;
    }

    /**
     * @param PFa the PFa to set
     */
    public void setPFa(Short PFa) {
        this.m_PFa = PFa;
    }

    /**
     * @return the PFb
     */
    public Short getPFb() {
        return m_PFb;
    }

    /**
     * @param PFb the PFb to set
     */
    public void setPFb(Short PFb) {
        this.m_PFb = PFb;
    }

    /**
     * @return the PFc
     */
    public Short getPFc() {
        return m_PFc;
    }

    /**
     * @param PFc the PFc to set
     */
    public void setPFc(Short PFc) {
        this.m_PFc = PFc;
    }

    /**
     * @return the P3Phases
     */
    public Short getP3Phases() {
        return m_P3Phases;
    }

    /**
     * @param P3Phases the P3Phases to set
     */
    public void setP3Phases(Short P3Phases) {
        this.m_P3Phases = P3Phases;
    }

    /**
     * @return the Q3Phases
     */
    public Short getQ3Phases() {
        return m_Q3Phases;
    }

    /**
     * @param Q3Phases the Q3Phases to set
     */
    public void setQ3Phases(Short Q3Phases) {
        this.m_Q3Phases = Q3Phases;
    }

    /**
     * @return the S3Phases
     */
    public Short getS3Phases() {
        return m_S3Phases;
    }

    /**
     * @param S3Phases the S3Phases to set
     */
    public void setS3Phases(Short S3Phases) {
        this.m_S3Phases = S3Phases;
    }

    /**
     * @return the PF3Phases
     */
    public Short getPF3Phases() {
        return m_PF3Phases;
    }

    /**
     * @param PF3Phases the PF3Phases to set
     */
    public void setPF3Phases(Short PF3Phases) {
        this.m_PF3Phases = PF3Phases;
    }

    /**
     * @return the G_RealFreq
     */
    public Short getG_RealFreq() {
        return m_G_RealFreq;
    }

    /**
     * @param G_RealFreq the G_RealFreq to set
     */
    public void setG_RealFreq(Short G_RealFreq) {
        this.m_G_RealFreq = G_RealFreq;
    }

    /**
     * @return the L_RealFreq
     */
    public Short getL_RealFreq() {
        return m_L_RealFreq;
    }

    /**
     * @param L_RealFreq the L_RealFreq to set
     */
    public void setL_RealFreq(Short L_RealFreq) {
        this.m_L_RealFreq = L_RealFreq;
    }

    /**
     * @return the I0
     */
    public Short getI0() {
        return m_I0;
    }

    /**
     * @param I0 the I0 to set
     */
    public void setI0(Short I0) {
        this.m_I0 = I0;
    }

    /**
     * @return the PowerEnergy
     */
    public Short getPowerEnergy() {
        return m_PowerEnergy;
    }

    /**
     * @param PowerEnergy the PowerEnergy to set
     */
    public void setPowerEnergy(Short PowerEnergy) {
        this.m_PowerEnergy = PowerEnergy;
    }

    /**
     * @return the SpeedGuardStatus
     */
    public Short getSpeedGuardStatus() {
        return m_SpeedGuardStatus;
    }

    /**
     * @param SpeedGuardStatus the SpeedGuardStatus to set
     */
    public void setSpeedGuardStatus(Short SpeedGuardStatus) {
        this.m_SpeedGuardStatus = SpeedGuardStatus;
    }

    /**
     * @return the RPM
     */
    public Short getRPM() {
        return m_RPM;
    }

    /**
     * @param RPM the RPM to set
     */
    public void setRPM(Short RPM) {
        this.m_RPM = RPM;
    }

    /**
     * @return the Temperature1
     */
    public Short getTemperature1() {
        return m_Temperature1;
    }

    /**
     * @param Temperature1 the Temperature1 to set
     */
    public void setTemperature1(Short Temperature1) {
        this.m_Temperature1 = Temperature1;
    }

    /**
     * @return the Temperature2
     */
    public Short getTemperature2() {
        return m_Temperature2;
    }

    /**
     * @param Temperature2 the Temperature2 to set
     */
    public void setTemperature2(Short Temperature2) {
        this.m_Temperature2 = Temperature2;
    }

    /**
     * @return the Temperature3
     */
    public Short getTemperature3() {
        return m_Temperature3;
    }

    /**
     * @param Temperature3 the Temperature3 to set
     */
    public void setTemperature3(Short Temperature3) {
        this.m_Temperature3 = Temperature3;
    }

    /**
     * @return the Temperature4
     */
    public Short getTemperature4() {
        return m_Temperature4;
    }

    /**
     * @param Temperature4 the Temperature4 to set
     */
    public void setTemperature4(Short Temperature4) {
        this.m_Temperature4 = Temperature4;
    }

    /**
     * @return the Temperature5
     */
    public Short getTemperature5() {
        return m_Temperature5;
    }

    /**
     * @param Temperature5 the Temperature5 to set
     */
    public void setTemperature5(Short Temperature5) {
        this.m_Temperature5 = Temperature5;
    }

    /**
     * @return the Temperature6
     */
    public Short getTemperature6() {
        return m_Temperature6;
    }

    /**
     * @param Temperature6 the Temperature6 to set
     */
    public void setTemperature6(Short Temperature6) {
        this.m_Temperature6 = Temperature6;
    }

    /**
     * @return the Temperature7
     */
    public Short getTemperature7() {
        return m_Temperature7;
    }

    /**
     * @param Temperature7 the Temperature7 to set
     */
    public void setTemperature7(Short Temperature7) {
        this.m_Temperature7 = Temperature7;
    }

    /**
     * @return the Temperature8
     */
    public Short getTemperature8() {
        return m_Temperature8;
    }

    /**
     * @param Temperature8 the Temperature8 to set
     */
    public void setTemperature8(Short Temperature8) {
        this.m_Temperature8 = Temperature8;
    }

    /**
     * @return the Temperature9
     */
    public Short getTemperature9() {
        return m_Temperature9;
    }

    /**
     * @param Temperature9 the Temperature9 to set
     */
    public void setTemperature9(Short Temperature9) {
        this.m_Temperature9 = Temperature9;
    }

    /**
     * @return the Temperature10
     */
    public Short getTemperature10() {
        return m_Temperature10;
    }

    /**
     * @param Temperature10 the Temperature10 to set
     */
    public void setTemperature10(Short Temperature10) {
        this.m_Temperature10 = Temperature10;
    }

    /**
     * @return the Temperature11
     */
    public Short getTemperature11() {
        return m_Temperature11;
    }

    /**
     * @param Temperature11 the Temperature11 to set
     */
    public void setTemperature11(Short Temperature11) {
        this.m_Temperature11 = Temperature11;
    }

    /**
     * @return the Temperature12
     */
    public Short getTemperature12() {
        return m_Temperature12;
    }

    /**
     * @param Temperature12 the Temperature12 to set
     */
    public void setTemperature12(Short Temperature12) {
        this.m_Temperature12 = Temperature12;
    }

    /**
     * @return the Press1
     */
    public Short getPress1() {
        return m_Press1;
    }

    /**
     * @param Press1 the Press1 to set
     */
    public void setPress1(Short Press1) {
        this.m_Press1 = Press1;
    }

    /**
     * @return the Press2
     */
    public Short getPress2() {
        return m_Press2;
    }

    /**
     * @param Press2 the Press2 to set
     */
    public void setPress2(Short Press2) {
        this.m_Press2 = Press2;
    }

    /**
     * @return the Press3
     */
    public Short getPress3() {
        return m_Press3;
    }

    /**
     * @param Press3 the Press3 to set
     */
    public void setPress3(Short Press3) {
        this.m_Press3 = Press3;
    }

    /**
     * @return the Press4
     */
    public Short getPress4() {
        return m_Press4;
    }

    /**
     * @param Press4 the Press4 to set
     */
    public void setPress4(Short Press4) {
        this.m_Press4 = Press4;
    }

    /**
     * @return the ContactGroup1
     */
    public Short getContactGroup1() {
        return m_ContactGroup1;
    }

    /**
     * @param ContactGroup1 the ContactGroup1 to set
     */
    public void setContactGroup1(Short ContactGroup1) {
        this.m_ContactGroup1 = ContactGroup1;
    }

    /**
     * @return the ContactGroup2
     */
    public Short getContactGroup2() {
        return m_ContactGroup2;
    }

    /**
     * @param ContactGroup2 the ContactGroup2 to set
     */
    public void setContactGroup2(Short ContactGroup2) {
        this.m_ContactGroup2 = ContactGroup2;
    }

    /**
     * @return the ContactGroup3
     */
    public Short getContactGroup3() {
        return m_ContactGroup3;
    }

    /**
     * @param ContactGroup3 the ContactGroup3 to set
     */
    public void setContactGroup3(Short ContactGroup3) {
        this.m_ContactGroup3 = ContactGroup3;
    }

    /**
     * @return the ContactGroup4
     */
    public Short getContactGroup4() {
        return m_ContactGroup4;
    }

    /**
     * @param ContactGroup4 the ContactGroup4 to set
     */
    public void setContactGroup4(Short ContactGroup4) {
        this.m_ContactGroup4 = ContactGroup4;
    }

    /**
     * @return the WaterOpenAngle
     */
    public Short getWaterOpenAngle() {
        return m_WaterOpenAngle;
    }

    /**
     * @param WaterOpenAngle the WaterOpenAngle to set
     */
    public void setWaterOpenAngle(Short WaterOpenAngle) {
        this.m_WaterOpenAngle = WaterOpenAngle;
    }

    /**
     * @return the FuelLevel1
     */
    public Short getFuelLevel1() {
        return m_FuelLevel1;
    }

    /**
     * @param FuelLevel1 the FuelLevel1 to set
     */
    public void setFuelLevel1(Short FuelLevel1) {
        this.m_FuelLevel1 = FuelLevel1;
    }

    /**
     * @return the FuelLevel2
     */
    public Short getFuelLevel2() {
        return m_FuelLevel2;
    }

    /**
     * @param FuelLevel2 the FuelLevel2 to set
     */
    public void setFuelLevel2(Short FuelLevel2) {
        this.m_FuelLevel2 = FuelLevel2;
    }

    /**
     * @return the ValvePlace
     */
    public Short getValvePlace() {
        return m_ValvePlace;
    }

    /**
     * @param ValvePlace the ValvePlace to set
     */
    public void setValvePlace(Short ValvePlace) {
        this.m_ValvePlace = ValvePlace;
    }

    /**
     * Reset all of the data to null
     */
    public final void resetAllValues() {
        this.setContactGroup1(null);
        this.setContactGroup2(null);
        this.setContactGroup3(null);
        this.setContactGroup4(null);

        this.setFuelLevel1(null);
        this.setFuelLevel2(null);

        this.setG_RealFreq(null);
        this.setL_RealFreq(null);

        this.setG_Ua(null);
        this.setG_Uab(null);
        this.setG_Ub(null);
        this.setG_Ubc(null);
        this.setG_Uc(null);
        this.setG_Uca(null);

        this.setI0(null);
        this.setIa(null);
        this.setIb(null);
        this.setIc(null);

        this.setL_Ua(null);
        this.setL_Uab(null);
        this.setL_Ub(null);
        this.setL_Ubc(null);
        this.setL_Uc(null);
        this.setL_Uca(null);

        this.setP3Phases(null);
        this.setPF3Phases(null);

        this.setPFa(null);
        this.setPFb(null);
        this.setPFc(null);

        this.setPa(null);
        this.setPb(null);
        this.setPc(null);

        this.setPowerEnergy(null);

        this.setPress1(null);
        this.setPress2(null);
        this.setPress3(null);
        this.setPress4(null);

        this.setQ3Phases(null);

        this.setQa(null);
        this.setQb(null);
        this.setQc(null);

        this.setRPM(null);

        this.setS3Phases(null);
        this.setSa(null);
        this.setSb(null);
        this.setSc(null);

        this.setSpeedGuardStatus(null);

        this.setTemperature1(null);
        this.setTemperature2(null);
        this.setTemperature3(null);
        this.setTemperature4(null);
        this.setTemperature5(null);
        this.setTemperature6(null);
        this.setTemperature7(null);
        this.setTemperature8(null);
        this.setTemperature9(null);
        this.setTemperature10(null);
        this.setTemperature11(null);
        this.setTemperature12(null);

        this.setValvePlace(null);
        this.setWaterOpenAngle(null);
    }
}
