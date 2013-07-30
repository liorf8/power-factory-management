package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G2UHistoryLineChartAssistance.class)
public class G2UHistoryLineChartAssistance extends HistoryLineChartAssistance{

    public G2UHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 2 - Ua", "Generator 2 - Ub", "Generator 2 - Uc");
                } catch (IOException ex) {
                    Logger.getLogger(G2UHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
