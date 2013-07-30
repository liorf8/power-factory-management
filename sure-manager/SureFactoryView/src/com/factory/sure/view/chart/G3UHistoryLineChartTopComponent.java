/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factory.sure.view.chart;

import com.factory.sure.chart.G3UHistoryLineChartAssistance;
import com.factory.sure.chart.HistoryLineChartFXMLController;
import com.factory.sure.chart.exception.ChartModeException;
import java.awt.BorderLayout;
import java.util.Collection;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.factory.sure.view.chart//G3UHistoryLineChart//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "G3UHistoryLineChartTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "com.factory.sure.view.chart.G3UHistoryLineChartTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_G3UHistoryLineChartAction",
        preferredID = "G3UHistoryLineChartTopComponent")
@Messages({
    "CTL_G3UHistoryLineChartAction=G3UHistoryLineChart",
    "CTL_G3UHistoryLineChartTopComponent=G3UHistoryLineChart Window",
    "HINT_G3UHistoryLineChartTopComponent=This is a G3UHistoryLineChart window"
})
public final class G3UHistoryLineChartTopComponent extends TopComponent {

    private JFXPanel m_pFXPanel;
    private G3UHistoryLineChartAssistance m_pG3UHistoryLineChartAssistance = null;

    public G3UHistoryLineChartTopComponent() {
        initComponents();
        setName(Bundle.CTL_G3UHistoryLineChartTopComponent());
        setToolTipText(Bundle.HINT_G3UHistoryLineChartTopComponent());

        Collection<? extends G3UHistoryLineChartAssistance> allHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G3UHistoryLineChartAssistance.class);
        for (G3UHistoryLineChartAssistance gu : allHistoryLineChartAssistances) {
            m_pG3UHistoryLineChartAssistance = gu;
            break;
        }

        if (m_pG3UHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G3UHistoryLineChartAssistance");
        }

        // Initialize the FXML
        initFXML();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        resolutionComboBox = new javax.swing.JComboBox<String>();
        jPanel3 = new javax.swing.JPanel();
        UaCheckBox = new javax.swing.JCheckBox();
        UbCheckBox = new javax.swing.JCheckBox();
        UcCheckBox = new javax.swing.JCheckBox();
        mainPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(G3UHistoryLineChartTopComponent.class, "G3UHistoryLineChartTopComponent.jLabel1.text")); // NOI18N

        resolutionComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "2 seconds", "10 seconds", "30 seconds", "5 minutes" }));
        resolutionComboBox.setSelectedIndex(0);
        resolutionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolutionComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        UaCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(UaCheckBox, org.openide.util.NbBundle.getMessage(G3UHistoryLineChartTopComponent.class, "G3UHistoryLineChartTopComponent.UaCheckBox.text")); // NOI18N
        jPanel3.add(UaCheckBox);

        UbCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(UbCheckBox, org.openide.util.NbBundle.getMessage(G3UHistoryLineChartTopComponent.class, "G3UHistoryLineChartTopComponent.UbCheckBox.text")); // NOI18N
        UbCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UbCheckBoxStateChanged(evt);
            }
        });
        UbCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbCheckBoxActionPerformed(evt);
            }
        });
        jPanel3.add(UbCheckBox);

        UcCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(UcCheckBox, org.openide.util.NbBundle.getMessage(G3UHistoryLineChartTopComponent.class, "G3UHistoryLineChartTopComponent.UcCheckBox.text")); // NOI18N
        UcCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UcCheckBoxStateChanged(evt);
            }
        });
        jPanel3.add(UcCheckBox);

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resolutionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolutionComboBoxActionPerformed
        final String resolution = (String) resolutionComboBox.getSelectedItem();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HistoryLineChartFXMLController controller = m_pG3UHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                    if (resolution.equals("2 seconds")) {
                        if (controller != null) {
                            controller.changeChartMode(HistoryLineChartFXMLController.CHART_MODE_2_SECONDS);
                        }
                    } else if (resolution.equals("10 seconds")) {
                        if (controller != null) {
                            controller.changeChartMode(HistoryLineChartFXMLController.CHART_MODE_10_SECONDS);
                        }
                    } else if (resolution.equals("30 seconds")) {
                        if (controller != null) {
                            controller.changeChartMode(HistoryLineChartFXMLController.CHART_MODE_30_SECONDS);
                        }
                    } else if (resolution.equals("5 minutes")) {
                        if (controller != null) {
                            controller.changeChartMode(HistoryLineChartFXMLController.CHART_MODE_5_MINUTES);
                        }
                    }
                } catch (ChartModeException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }//GEN-LAST:event_resolutionComboBoxActionPerformed

    private void UbCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UbCheckBoxStateChanged
        final boolean state = UbCheckBox.isSelected();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HistoryLineChartFXMLController controller = m_pG3UHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                if (controller != null) {
                    controller.switchDataSeriesStatus(2, state);
                }
            }
        });
    }//GEN-LAST:event_UbCheckBoxStateChanged

    private void UbCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UbCheckBoxActionPerformed

    private void UcCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UcCheckBoxStateChanged
        final boolean state = UcCheckBox.isSelected();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HistoryLineChartFXMLController controller = m_pG3UHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                if (controller != null) {
                    controller.switchDataSeriesStatus(3, state);
                }
            }
        });
    }//GEN-LAST:event_UcCheckBoxStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox UaCheckBox;
    private javax.swing.JCheckBox UbCheckBox;
    private javax.swing.JCheckBox UcCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox resolutionComboBox;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
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

    private void initFXML() {
        m_pFXPanel = new JFXPanel();
        mainPanel.add(m_pFXPanel, BorderLayout.CENTER);
        Platform.setImplicitExit(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane stackPane = m_pG3UHistoryLineChartAssistance.getStackPane();
                Scene scene = new Scene(stackPane);
                m_pFXPanel.setScene(scene);

                HistoryLineChartFXMLController controller = m_pG3UHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                controller.initGraph(stackPane, "Generator 3 voltage");
            }
        });
    }
}