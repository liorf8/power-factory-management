package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G1IHistoryLineChartAssistance.class)
public class G1IHistoryLineChartAssistance extends HistoryLineChartAssistance{
    public G1IHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 1 - Ia", "Generator 1 - Ib", "Generator 1 - Ic");
                } catch (IOException ex) {
                    Logger.getLogger(G1IHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
