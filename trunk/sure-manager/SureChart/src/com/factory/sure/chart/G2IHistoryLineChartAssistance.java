package com.factory.sure.chart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = G2IHistoryLineChartAssistance.class)
public class G2IHistoryLineChartAssistance extends HistoryLineChartAssistance{

    public G2IHistoryLineChartAssistance() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHistoryLineChartFXMLController().initGraphData("Generator 2 - Ia", "Generator 2 - Ib", "Generator 2 - Ic");
                } catch (IOException ex) {
                    Logger.getLogger(G2IHistoryLineChartAssistance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
