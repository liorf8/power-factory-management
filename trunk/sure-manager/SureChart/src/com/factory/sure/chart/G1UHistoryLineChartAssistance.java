package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G1UHistoryLineChartAssistance.class)
public class G1UHistoryLineChartAssistance extends HistoryLineChartAssistance{

    public G1UHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 1 - Ua", "Generator 1 - Ub", "Generator 1 - Uc");
                } catch (IOException ex) {
                    Logger.getLogger(G1UHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
