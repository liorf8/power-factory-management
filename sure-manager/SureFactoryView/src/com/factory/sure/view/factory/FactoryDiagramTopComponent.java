/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.view.factory;

import com.factory.sure.chart.G1IHistoryLineChartAssistance;
import com.factory.sure.chart.G1UHistoryLineChartAssistance;
import com.factory.sure.chart.G2IHistoryLineChartAssistance;
import com.factory.sure.chart.G2UHistoryLineChartAssistance;
import com.factory.sure.chart.G3IHistoryLineChartAssistance;
import com.factory.sure.chart.G3UHistoryLineChartAssistance;
import com.factory.sure.chart.LUHistoryLineChartAssistance;
import com.factory.sure.comport.ComportAssistance;
import com.factory.sure.comport.helper.constants.ModbusConstants;
import com.factory.sure.data.pojos.GeneratorData;
import com.factory.sure.data.FactoryDataAssistance;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Collection;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.factory.sure.view.factory//FactoryDiagram//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "FactoryDiagramTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.factory.sure.view.factory.FactoryDiagramTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FactoryDiagramAction",
        preferredID = "FactoryDiagramTopComponent")
@Messages({
    "CTL_FactoryDiagramAction=FactoryDiagram",
    "CTL_FactoryDiagramTopComponent=FactoryDiagram Window",
    "HINT_FactoryDiagramTopComponent=This is a FactoryDiagram window"
})
public final class FactoryDiagramTopComponent extends TopComponent implements LookupListener {

    private static JFXPanel fxPanel;
    private ComportAssistance m_pComportAssistance = null;
    private FactoryDataAssistance m_pFactoryDataAssistance = null;
    private Lookup.Result<GeneratorData> m_pLookupResult;
    private ImageTestFXMLController m_pImageTestFXMLController;
    /*
     * Line history chart assistances
     */
    private LUHistoryLineChartAssistance m_pLUHistoryLineChartAssistance = null;
    /*
     * Generator 1 history chart assistances
     */
    private G1UHistoryLineChartAssistance m_pG1UHistoryLineChartAssistance = null;
    private G1IHistoryLineChartAssistance m_pG1IHistoryLineChartAssistance = null;
    /*
     * Generator 2 history chart assistances
     */
    private G2UHistoryLineChartAssistance m_pG2UHistoryLineChartAssistance = null;
    private G2IHistoryLineChartAssistance m_pG2IHistoryLineChartAssistance = null;
    /*
     * Generator 3 history chart assistances
     */
    private G3UHistoryLineChartAssistance m_pG3UHistoryLineChartAssistance = null;
    private G3IHistoryLineChartAssistance m_pG3IHistoryLineChartAssistance = null;

    // Database update interval in milliseconds
    public FactoryDiagramTopComponent() {
        initComponents();
        setName(Bundle.CTL_FactoryDiagramTopComponent());
        setToolTipText(Bundle.HINT_FactoryDiagramTopComponent());
        setLayout(new BorderLayout());
        init();

        Collection<? extends ComportAssistance> allComportAssistances = Lookup.getDefault().lookupAll(ComportAssistance.class);
        for (ComportAssistance comportAssistance : allComportAssistances) {
            m_pComportAssistance = comportAssistance;
            break;  // Only have one ComportAssistance Instance
        }
        if (m_pComportAssistance != null) {
            m_pComportAssistance.setComPort("COM6");
            m_pComportAssistance.initializeUART();
//            m_pComportAssistance.startUART();
        } else {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of ComportAssistance");
        }

        // Find FactoryDataAssistance service provider
        Collection<? extends FactoryDataAssistance> allFactoryDataAssistances = Lookup.getDefault().lookupAll(FactoryDataAssistance.class);
        for (FactoryDataAssistance factoryDataAssistance : allFactoryDataAssistances) {
            m_pFactoryDataAssistance = factoryDataAssistance;
            break;
        }
        if (m_pFactoryDataAssistance != null) {
            m_pFactoryDataAssistance.startDatabaseTimer();
        } else {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of FactoryDataAssistance");
        }

        // Find LUHistoryLineChartAssistance service provider
        Collection<? extends LUHistoryLineChartAssistance> allLUHistoryLineChartAssistances = Lookup.getDefault().lookupAll(LUHistoryLineChartAssistance.class);
        for (LUHistoryLineChartAssistance lu : allLUHistoryLineChartAssistances) {
            m_pLUHistoryLineChartAssistance = lu;
            break;
        }
        if (m_pLUHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of LUHistoryLineChartAssistance");
        }

        // Find G1UHistoryLineChartAssistance service provider
        Collection<? extends G1UHistoryLineChartAssistance> allG1UHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G1UHistoryLineChartAssistance.class);
        for (G1UHistoryLineChartAssistance gu : allG1UHistoryLineChartAssistances) {
            m_pG1UHistoryLineChartAssistance = gu;
            break;
        }
        if (m_pG1UHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G1UHistoryLineChartAssistance");
        }

        // Find G1IHistoryLineChartAssistance service provider
        Collection<? extends G1IHistoryLineChartAssistance> allG1IHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G1IHistoryLineChartAssistance.class);
        for (G1IHistoryLineChartAssistance gi : allG1IHistoryLineChartAssistances) {
            m_pG1IHistoryLineChartAssistance = gi;
            break;
        }
        if (m_pG1IHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G1IHistoryLineChartAssistance");
        }

        // Find G2UHistoryLineChartAssistance service provider
        Collection<? extends G2UHistoryLineChartAssistance> allG2UHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G2UHistoryLineChartAssistance.class);
        for (G2UHistoryLineChartAssistance gu : allG2UHistoryLineChartAssistances) {
            m_pG2UHistoryLineChartAssistance = gu;
            break;
        }
        if (m_pG2UHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G2UHistoryLineChartAssistance");
        }
        
        // Find G2IHistoryLineChartAssistance service provider
        Collection<? extends G2IHistoryLineChartAssistance> allG2IHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G2IHistoryLineChartAssistance.class);
        for (G2IHistoryLineChartAssistance gi : allG2IHistoryLineChartAssistances) {
            m_pG2IHistoryLineChartAssistance = gi;
            break;
        }
        if (m_pG2IHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G2IHistoryLineChartAssistance");
        }

        // Find G3UHistoryLineChartAssistance service provider
        Collection<? extends G3UHistoryLineChartAssistance> allG3UHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G3UHistoryLineChartAssistance.class);
        for (G3UHistoryLineChartAssistance gu : allG3UHistoryLineChartAssistances) {
            m_pG3UHistoryLineChartAssistance = gu;
            break;
        }
        if (m_pG3UHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G3UHistoryLineChartAssistance");
        }
        
        // Find G3IHistoryLineChartAssistance service provider
        Collection<? extends G3IHistoryLineChartAssistance> allG3IHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G3IHistoryLineChartAssistance.class);
        for (G3IHistoryLineChartAssistance gi : allG3IHistoryLineChartAssistances) {
            m_pG3IHistoryLineChartAssistance = gi;
            break;
        }
        if (m_pG3IHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G3IHistoryLineChartAssistance");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        this.m_pLookupResult = this.m_pComportAssistance.getLookup().lookupResult(GeneratorData.class);
        this.m_pLookupResult.allItems();
        this.m_pLookupResult.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        this.m_pLookupResult.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public void init() {
        fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);
        Platform.setImplicitExit(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImageTestFXML.fxml"));
                    StackPane root = (StackPane) fxmlLoader.load();
                    Scene scene = new Scene(root);
                    fxPanel.setScene(scene);
                    m_pImageTestFXMLController = (ImageTestFXMLController) fxmlLoader.getController();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    @Override
    public void resultChanged(LookupEvent le) {
        final Collection<? extends GeneratorData> generatorDatas = this.m_pLookupResult.allInstances();
        GeneratorData changedGeneratorData = null;
        for (GeneratorData generatorData : generatorDatas) {
            changedGeneratorData = generatorData;
            break;
        }

        if (changedGeneratorData != null) {
            // Update the view if available
            final GeneratorData g = changedGeneratorData;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Result changes");
                    m_pImageTestFXMLController.updateGUI(g);
                    Long timeValueInSecond = System.currentTimeMillis() / 1000;

                    // Update line voltage, current history chart value
                    m_pLUHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getL_Ua(), g.getL_Ub(), g.getL_Uc());

                    // Update generator voltage, current history chart value
                    switch (g.getGenerator().getModbusAddress()) {
                        case ModbusConstants.GENERATOR_1_MODBUS_ADDRESS:
                            m_pG1UHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getG_Ua(), g.getG_Ub(), g.getG_Uc());
                            m_pG1IHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getIa(), g.getIb(), g.getIc());
                            break;
                        case ModbusConstants.GENERATOR_2_MODBUS_ADDRESS:
                            m_pG2UHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getG_Ua(), g.getG_Ub(), g.getG_Uc());                            
                            m_pG2IHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getIa(), g.getIb(), g.getIc());
                            break;
                        case ModbusConstants.GENERATOR_3_MODBUS_ADDRESS:
                            m_pG3UHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getG_Ua(), g.getG_Ub(), g.getG_Uc());                            
                            m_pG3IHistoryLineChartAssistance.getHistoryLineChartFXMLController().addToDataSeries(timeValueInSecond, g.getIa(), g.getIb(), g.getIc());
                            break;
                        default:
                            System.err.println("FactoryDiagramTopComponent: resultChanged: modbus address not available");
                            break;
                    }
                }
            });

            this.m_pFactoryDataAssistance.update(changedGeneratorData);


        }
    }
}
