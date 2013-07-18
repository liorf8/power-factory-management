/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.view.factory;

import com.factory.sure.comport.ComportAssistance;
import com.factory.sure.comport.data.GeneratorData;
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
    private ComportAssistance m_pComportAssistance;
    private Lookup.Result<GeneratorData> m_pLookupResult;
    private ImageTestFXMLController m_pImageTestFXMLController;

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
        m_pComportAssistance.setComPort("COM6");
        m_pComportAssistance.initializeUART();
        m_pComportAssistance.startUART();
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Result changes");
                for (GeneratorData generatorData : generatorDatas) {
                    m_pImageTestFXMLController.updateGUI(generatorData);
                }
            }
        });

    }
}
