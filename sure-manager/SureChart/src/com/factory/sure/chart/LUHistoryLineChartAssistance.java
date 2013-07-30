package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = LUHistoryLineChartAssistance.class)
public class LUHistoryLineChartAssistance extends HistoryLineChartAssistance{
    public LUHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Line Ua", "Line Ub", "Line Uc");
                } catch (IOException ex) {
                    Logger.getLogger(LUHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
