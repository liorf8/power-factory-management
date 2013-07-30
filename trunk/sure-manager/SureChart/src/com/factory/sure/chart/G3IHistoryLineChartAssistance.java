package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G3IHistoryLineChartAssistance.class)
public class G3IHistoryLineChartAssistance extends HistoryLineChartAssistance {

    public G3IHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 3 - Ia", "Generator 3 - Ib", "Generator 3 - Ic");
                } catch (IOException ex) {
                    Logger.getLogger(G3IHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
