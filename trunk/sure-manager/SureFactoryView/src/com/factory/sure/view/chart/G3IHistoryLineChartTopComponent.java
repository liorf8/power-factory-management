package com.factory.sure.view.chart;

import com.factory.sure.chart.G3IHistoryLineChartAssistance;
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
        dtd = "-//com.factory.sure.view.chart//G3IHistoryLineChart//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "G3IHistoryLineChartTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "com.factory.sure.view.chart.G3IHistoryLineChartTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_G3IHistoryLineChartAction",
        preferredID = "G3IHistoryLineChartTopComponent")
@Messages({
    "CTL_G3IHistoryLineChartAction=G3IHistoryLineChart",
    "CTL_G3IHistoryLineChartTopComponent=G3IHistoryLineChart Window",
    "HINT_G3IHistoryLineChartTopComponent=This is a G3IHistoryLineChart window"
})
public final class G3IHistoryLineChartTopComponent extends TopComponent {

    private JFXPanel m_pFXPanel;
    private G3IHistoryLineChartAssistance m_pG3IHistoryLineChartAssistance = null;

    public G3IHistoryLineChartTopComponent() {
        initComponents();
        setName(Bundle.CTL_G3IHistoryLineChartTopComponent());
        setToolTipText(Bundle.HINT_G3IHistoryLineChartTopComponent());

        Collection<? extends G3IHistoryLineChartAssistance> allHistoryLineChartAssistances = Lookup.getDefault().lookupAll(G3IHistoryLineChartAssistance.class);
        for (G3IHistoryLineChartAssistance gi : allHistoryLineChartAssistances) {
            m_pG3IHistoryLineChartAssistance = gi;
            break;
        }

        if (m_pG3IHistoryLineChartAssistance == null) {
            System.err.println("FactoryDiagramTopComponent: Cannot find and instance of G3IHistoryLineChartAssistance");
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
        jPanel3 = new javax.swing.JPanel();
        IaCheckBox = new javax.swing.JCheckBox();
        IbCheckBox = new javax.swing.JCheckBox();
        IcCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        resolutionComboBox = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();

        topPanel.setPreferredSize(new java.awt.Dimension(800, 20));

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        IaCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(IaCheckBox, org.openide.util.NbBundle.getMessage(G3IHistoryLineChartTopComponent.class, "G3IHistoryLineChartTopComponent.IaCheckBox.text")); // NOI18N
        jPanel3.add(IaCheckBox);

        IbCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(IbCheckBox, org.openide.util.NbBundle.getMessage(G3IHistoryLineChartTopComponent.class, "G3IHistoryLineChartTopComponent.IbCheckBox.text")); // NOI18N
        IbCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                IbCheckBoxStateChanged(evt);
            }
        });
        IbCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IbCheckBoxActionPerformed(evt);
            }
        });
        jPanel3.add(IbCheckBox);

        IcCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(IcCheckBox, org.openide.util.NbBundle.getMessage(G3IHistoryLineChartTopComponent.class, "G3IHistoryLineChartTopComponent.IcCheckBox.text")); // NOI18N
        IcCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                IcCheckBoxStateChanged(evt);
            }
        });
        jPanel3.add(IcCheckBox);

        resolutionComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "2 seconds", "10 seconds", "30 seconds", "5 minutes" }));
        resolutionComboBox.setSelectedIndex(0);
        resolutionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolutionComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(G3IHistoryLineChartTopComponent.class, "G3IHistoryLineChartTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void IbCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_IbCheckBoxStateChanged
        final boolean state = IbCheckBox.isSelected();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HistoryLineChartFXMLController controller = m_pG3IHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                if (controller != null) {
                    controller.switchDataSeriesStatus(2, state);
                }
            }
        });
    }//GEN-LAST:event_IbCheckBoxStateChanged

    private void IbCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IbCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IbCheckBoxActionPerformed

    private void IcCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_IcCheckBoxStateChanged
        final boolean state = IcCheckBox.isSelected();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HistoryLineChartFXMLController controller = m_pG3IHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                if (controller != null) {
                    controller.switchDataSeriesStatus(3, state);
                }
            }
        });
    }//GEN-LAST:event_IcCheckBoxStateChanged

    private void resolutionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolutionComboBoxActionPerformed
        final String resolution = (String) resolutionComboBox.getSelectedItem();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HistoryLineChartFXMLController controller = m_pG3IHistoryLineChartAssistance.getHistoryLineChartFXMLController();
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox IaCheckBox;
    private javax.swing.JCheckBox IbCheckBox;
    private javax.swing.JCheckBox IcCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
                StackPane stackPane = m_pG3IHistoryLineChartAssistance.getStackPane();
                Scene scene = new Scene(stackPane);
                m_pFXPanel.setScene(scene);

                HistoryLineChartFXMLController controller = m_pG3IHistoryLineChartAssistance.getHistoryLineChartFXMLController();
                controller.initGraph(stackPane, "Generator 3 current");
            }
        });
    }
}
