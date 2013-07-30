package com.factory.sure.chart;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class HistoryLineChartAssistance {

    private FXMLLoader m_pFXMLLoader = null;
    private HistoryLineChartFXMLController m_pHistoryLineChartFXMLController;
    private StackPane m_pStackPane;

    public HistoryLineChartAssistance() {
    }

    /**
     * Initialize the line chart assistance, must be called before any other call.
     * @throws IOException 
     */
    protected void init() throws IOException {
        m_pFXMLLoader = new FXMLLoader(getClass().getResource("HistoryLineChartFXML.fxml"));
        m_pStackPane = (StackPane) m_pFXMLLoader.load();
        m_pHistoryLineChartFXMLController = (HistoryLineChartFXMLController) m_pFXMLLoader.getController();
    }

    public FXMLLoader getFXMLLoader() {
        return m_pFXMLLoader;
    }

    public HistoryLineChartFXMLController getHistoryLineChartFXMLController() {
        return m_pHistoryLineChartFXMLController;
    }

    public StackPane getStackPane() {
        return m_pStackPane;
    }
}
