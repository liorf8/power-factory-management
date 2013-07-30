package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G3UHistoryLineChartAssistance.class)
public class G3UHistoryLineChartAssistance extends HistoryLineChartAssistance{
    public G3UHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 3 - Ua", "Generator 3 - Ub", "Generator 3 - Uc");
                } catch (IOException ex) {
                    Logger.getLogger(G3UHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
