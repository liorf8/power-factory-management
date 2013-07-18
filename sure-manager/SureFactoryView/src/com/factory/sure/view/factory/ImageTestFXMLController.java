/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.view.factory;

import com.factory.sure.comport.data.GeneratorData;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 50801_000
 */
public class ImageTestFXMLController implements Initializable {

    @FXML
    private Label L_Uab_Label_Val;
    @FXML
    private Label L_Ubc_Label_Val;
    @FXML
    private Label L_Uca_Label_Val;
    @FXML
    private Label G1_Uab_Label_Val;
    @FXML
    private Label G1_Ubc_Label_Val;
    @FXML
    private Label G1_Uca_Label_Val;
    @FXML
    private Label G1_Ia_Label_Val;
    @FXML
    private Label G1_Ib_Label_Val;
    @FXML
    private Label G1_Ic_Label_Val;
    @FXML
    private Label G2_Uab_Label_Val;
    @FXML
    private Label G2_Ubc_Label_Val;
    @FXML
    private Label G2_Uca_Label_Val;
    @FXML
    private Label G2_Ia_Label_Val;
    @FXML
    private Label G2_Ib_Label_Val;
    @FXML
    private Label G2_Ic_Label_Val;
    @FXML
    private Label G3_Uab_Label_Val;
    @FXML
    private Label G3_Ubc_Label_Val;
    @FXML
    private Label G3_Uca_Label_Val;
    @FXML
    private Label G3_Ia_Label_Val;
    @FXML
    private Label G3_Ib_Label_Val;
    @FXML
    private Label G3_Ic_Label_Val;
//    private JFXPanel m_pFXPanel;

//    public ImageTestFXMLController(JFXPanel fXPanel) {
//        this.m_pFXPanel = fXPanel;
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void updateGUI(GeneratorData generatorData) {
        L_Uab_Label_Val.setText(generatorData.getL_Uab().toString());
        L_Ubc_Label_Val.setText(generatorData.getL_Ubc().toString());
        L_Uca_Label_Val.setText(generatorData.getL_Uca().toString());
        switch (generatorData.getModbusAddress()) {
            case ModbusConstants.GENERATOR_1_MODBUS_ADDRESS:
                G1_Uab_Label_Val.setText(generatorData.getG_Uab().toString());
                G1_Ubc_Label_Val.setText(generatorData.getG_Ubc().toString());
                G1_Uca_Label_Val.setText(generatorData.getG_Uca().toString());
                G1_Ia_Label_Val.setText(generatorData.getIa().toString());
                G1_Ib_Label_Val.setText(generatorData.getIb().toString());
                G1_Ic_Label_Val.setText(generatorData.getIc().toString());
                break;
            case ModbusConstants.GENERATOR_2_MODBUS_ADDRESS:
                G2_Uab_Label_Val.setText(generatorData.getG_Uab().toString());
                G2_Ubc_Label_Val.setText(generatorData.getG_Ubc().toString());
                G2_Uca_Label_Val.setText(generatorData.getG_Uca().toString());
                G2_Ia_Label_Val.setText(generatorData.getIa().toString());
                G2_Ib_Label_Val.setText(generatorData.getIb().toString());
                G2_Ic_Label_Val.setText(generatorData.getIc().toString());
                break;
            case ModbusConstants.GENERATOR_3_MODBUS_ADDRESS:
                G3_Uab_Label_Val.setText(generatorData.getG_Uab().toString());
                G3_Ubc_Label_Val.setText(generatorData.getG_Ubc().toString());
                G3_Uca_Label_Val.setText(generatorData.getG_Uca().toString());
                G3_Ia_Label_Val.setText(generatorData.getIa().toString());
                G3_Ib_Label_Val.setText(generatorData.getIb().toString());
                G3_Ic_Label_Val.setText(generatorData.getIc().toString());
                break;
            default:
                break;
        }
    }

    public Label getL_Uab_Label_Val() {
        return L_Uab_Label_Val;
    }

    public Label getL_Ubc_Label_Val() {
        return L_Ubc_Label_Val;
    }

    public Label getL_Uca_Label_Val() {
        return L_Uca_Label_Val;
    }

    public Label getG1_Uab_Label_Val() {
        return G1_Uab_Label_Val;
    }

    public Label getG1_Ubc_Label_Val() {
        return G1_Ubc_Label_Val;
    }

    public Label getG1_Uca_Label_Val() {
        return G1_Uca_Label_Val;
    }

    public Label getG1_Ia_Label_Val() {
        return G1_Ia_Label_Val;
    }

    public Label getG1_Ib_Label_Val() {
        return G1_Ib_Label_Val;
    }

    public Label getG1_Ic_Label_Val() {
        return G1_Ic_Label_Val;
    }

    public Label getG2_Uab_Label_Val() {
        return G2_Uab_Label_Val;
    }

    public Label getG2_Ubc_Label_Val() {
        return G2_Ubc_Label_Val;
    }

    public Label getG2_Uca_Label_Val() {
        return G2_Uca_Label_Val;
    }

    public Label getG2_Ia_Label_Val() {
        return G2_Ia_Label_Val;
    }

    public Label getG2_Ib_Label_Val() {
        return G2_Ib_Label_Val;
    }

    public Label getG2_Ic_Label_Val() {
        return G2_Ic_Label_Val;
    }

    public Label getG3_Uab_Label_Val() {
        return G3_Uab_Label_Val;
    }

    public Label getG3_Ubc_Label_Val() {
        return G3_Ubc_Label_Val;
    }

    public Label getG3_Uca_Label_Val() {
        return G3_Uca_Label_Val;
    }

    public Label getG3_Ia_Label_Val() {
        return G3_Ia_Label_Val;
    }

    public Label getG3_Ib_Label_Val() {
        return G3_Ib_Label_Val;
    }

    public Label getG3_Ic_Label_Val() {
        return G3_Ic_Label_Val;
    }
}
