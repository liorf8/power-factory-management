package com.factory.sure.chart;

import com.factory.sure.chart.exception.ChartModeException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class HistoryLineChartFXMLController implements Initializable {

    /*
     * The color of each data series line
     */
    private static final String COLOR_DATA_SERIES_1 = "#ffff66";
    private static final String COLOR_DATA_SERIES_2 = "#ff3333";
    private static final String COLOR_DATA_SERIES_3 = "#3333ff";
    private static final String COLOR_TRANSPARENT = "transparent";
    /*
     * The chart mode
     */
    public static final int CHART_MODE_2_SECONDS = 0;  // Default chart mode
    public static final int CHART_MODE_10_SECONDS = 1;
    public static final int CHART_MODE_30_SECONDS = 2;
    public static final int CHART_MODE_5_MINUTES = 3;
    public int mChartMode = CHART_MODE_2_SECONDS;
    /*
     * Other constants
     */
    private static final int HISTORY_SIZE = 50;        // The number of values in a history queue
    private static final int X_AXIS_RANGE_2_SECOND_MODE = 2 * HISTORY_SIZE;
    private static final int X_AXIS_TICK_2_SECOND_MODE = 2 * 5;
    private static final int X_AXIS_RANGE_10_SECOND_MODE = 10 * HISTORY_SIZE;
    private static final int X_AXIS_TICK_10_SECOND_MODE = 10 * 5;
    private static final int X_AXIS_RANGE_30_SECOND_MODE = 30 * HISTORY_SIZE;
    private static final int X_AXIS_TICK_30_SECOND_MODE = 30 * 5;
    private static final int X_AXIS_RANGE_5_MINUTE_MODE = 300 * HISTORY_SIZE;
    private static final int X_AXIS_TICK_5_MINUTE_MODE = 300 * 5;
    /*
     * Private variables
     */
//    private double mXIndex = 0;
//    private boolean mFirstData = true;  // This is for intializing the XAxis 
    private LineChart<Number, Number> m_pLineChart = null;
    private NumberAxis m_pXAxis, m_pYAxis;
    private StackPane m_pStackPane = null;
    private String m_pLineChartTitle = null;
    private String m_pDataSeriesName1 = null;
    private String m_pDataSeriesName2 = null;
    private String m_pDataSeriesName3 = null;
    private Timeline m_pAnimation;
    private Long mTimeInSecond = null;
    private Float mPlotValue1 = null;
    private Float mPlotValue2 = null;
    private Float mPlotValue3 = null;
    private Lock m_pLock;
    private boolean mToPlotDataSeries1 = true;
    private boolean mToPlotDataSeries2 = true;
    private boolean mToPlotDataSeries3 = true;
    /*
     * The data series for each mode
     */
    private XYChart.Series<Number, Number> m_pDataSeries1_2SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries2_2SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries3_2SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries1_10SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries2_10SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries3_10SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries1_30SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries2_30SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries3_30SEC = null;
    private XYChart.Series<Number, Number> m_pDataSeries1_5MIN = null;
    private XYChart.Series<Number, Number> m_pDataSeries2_5MIN = null;
    private XYChart.Series<Number, Number> m_pDataSeries3_5MIN = null;
    /*
     * The data observable lists for each resolution
     */
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList1_2SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList2_2SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList3_2SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList1_10SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList2_10SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList3_10SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList1_30SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList2_30SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList3_30SEC = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList1_5MIN = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList2_5MIN = null;
    private ObservableList<XYChart.Data<Number, Number>> m_pDataSerieObservableList3_5MIN = null;
    /*
     * The counter to determine whether to add a data to a series for a chart mode
     */
    private int mChartModeCounter = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m_pLock = new ReentrantLock();
    }

    /**
     * Initialize the data of the history graph, must be called before the
     * initGraph function.
     *
     * @param dataSeriesName1
     * @param dataSeriesName2
     * @param dataSeriesName3
     */
    public void initGraphData(String dataSeriesName1, String dataSeriesName2, String dataSeriesName3) {
        m_pDataSeriesName1 = dataSeriesName1;
        m_pDataSeriesName2 = dataSeriesName2;
        m_pDataSeriesName3 = dataSeriesName3;

        initDataSeries(m_pDataSeriesName1, m_pDataSeriesName2, m_pDataSeriesName3);

        m_pAnimation = new Timeline();
        m_pAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    m_pLock.lock();
                    if (mTimeInSecond != null) {

                        addToDataSeries1(mTimeInSecond, mPlotValue1, CHART_MODE_2_SECONDS);
                        addToDataSeries2(mTimeInSecond, mPlotValue2, CHART_MODE_2_SECONDS);
                        addToDataSeries3(mTimeInSecond, mPlotValue3, CHART_MODE_2_SECONDS);

                        if (mChartModeCounter % 10 == 0) {
                            addToDataSeries1(mTimeInSecond, mPlotValue1, CHART_MODE_10_SECONDS);
                            addToDataSeries2(mTimeInSecond, mPlotValue2, CHART_MODE_10_SECONDS);
                            addToDataSeries3(mTimeInSecond, mPlotValue3, CHART_MODE_10_SECONDS);
                        }

                        if (mChartModeCounter % 30 == 0) {
                            addToDataSeries1(mTimeInSecond, mPlotValue1, CHART_MODE_30_SECONDS);
                            addToDataSeries2(mTimeInSecond, mPlotValue2, CHART_MODE_30_SECONDS);
                            addToDataSeries3(mTimeInSecond, mPlotValue3, CHART_MODE_30_SECONDS);
                        }

                        if (mChartModeCounter % 300 == 0) {
                            addToDataSeries1(mTimeInSecond, mPlotValue1, CHART_MODE_5_MINUTES);
                            addToDataSeries2(mTimeInSecond, mPlotValue2, CHART_MODE_5_MINUTES);
                            addToDataSeries3(mTimeInSecond, mPlotValue3, CHART_MODE_5_MINUTES);
                            mChartModeCounter = 0;
                        }

                        mChartModeCounter += 2;

                        // Set null after plotting
                        mTimeInSecond = null;
                        mPlotValue1 = null;
                        mPlotValue2 = null;
                        mPlotValue3 = null;

                        // Update the X Axis if it has been initialized
                        if (m_pXAxis != null) {
                            int chartRange;
                            ObservableList<XYChart.Data<Number, Number>> observableList1, observableList2, observableList3;
                            switch (mChartMode) {
                                case CHART_MODE_2_SECONDS:
                                    observableList1 = m_pDataSerieObservableList1_2SEC;
                                    observableList2 = m_pDataSerieObservableList2_2SEC;
                                    observableList3 = m_pDataSerieObservableList3_2SEC;
                                    chartRange = X_AXIS_RANGE_2_SECOND_MODE;
                                    break;
                                case CHART_MODE_10_SECONDS:
                                    observableList1 = m_pDataSerieObservableList1_10SEC;
                                    observableList2 = m_pDataSerieObservableList2_10SEC;
                                    observableList3 = m_pDataSerieObservableList3_10SEC;
                                    chartRange = X_AXIS_RANGE_10_SECOND_MODE;
                                    break;
                                case CHART_MODE_30_SECONDS:
                                    observableList1 = m_pDataSerieObservableList1_30SEC;
                                    observableList2 = m_pDataSerieObservableList2_30SEC;
                                    observableList3 = m_pDataSerieObservableList3_30SEC;
                                    chartRange = X_AXIS_RANGE_30_SECOND_MODE;
                                    break;
                                case CHART_MODE_5_MINUTES:
                                    observableList1 = m_pDataSerieObservableList1_5MIN;
                                    observableList2 = m_pDataSerieObservableList2_5MIN;
                                    observableList3 = m_pDataSerieObservableList3_5MIN;
                                    chartRange = X_AXIS_RANGE_5_MINUTE_MODE;
                                    break;
                                default:
                                    observableList1 = m_pDataSerieObservableList1_2SEC;
                                    observableList2 = m_pDataSerieObservableList2_2SEC;
                                    observableList3 = m_pDataSerieObservableList3_2SEC;
                                    chartRange = X_AXIS_RANGE_2_SECOND_MODE;
                                    break;
                            }


                            double maxValue = Math.max(Math.max(observableList1.get(observableList1.size() - 1).getXValue().doubleValue(),
                                    observableList2.get(observableList1.size() - 1).getXValue().doubleValue()),
                                    observableList3.get(observableList1.size() - 1).getXValue().doubleValue());
                            double newLowerBound = maxValue - chartRange;
                            double newUpperBound = maxValue;
                            m_pXAxis.setLowerBound(newLowerBound);
                            m_pXAxis.setUpperBound(newUpperBound);
                            m_pXAxis.setDisable(false);
                        }
                    }
                } finally {
                    m_pLock.unlock();
                }
            }
        }));
        m_pAnimation.setCycleCount(Timeline.INDEFINITE);
        m_pAnimation.play();
    }

    /**
     * Initialize the graph, must be called after the initGraphData function.
     *
     * @param stackPane
     * @param lineChartTitle
     */
    public void initGraph(StackPane stackPane, String lineChartTitle) {
        m_pLineChartTitle = lineChartTitle;
        m_pStackPane = stackPane;

        createChart(m_pLineChartTitle);
        if (m_pStackPane.getChildren().size() > 0) {
            m_pStackPane.getChildren().remove(0);   // Remove the last line chart if there is
        }
        m_pStackPane.getChildren().add(m_pLineChart);
        addOrReplaceDataSeries();
    }

    /**
     * Enable or disable a data series, assume that line chart has been
     * initialized
     *
     * @param dataSeriesNumber
     * @param status
     */
    public void switchDataSeriesStatus(int dataSeriesNumber, boolean status) {
        XYChart.Series<Number, Number> dataSeries1, dataSeries2, dataSeries3;
        int chartMode;
        try {
            m_pLock.lock();
            chartMode = mChartMode;
        } finally {
            m_pLock.unlock();
        }

        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
                dataSeries1 = m_pDataSeries1_2SEC;
                dataSeries2 = m_pDataSeries2_2SEC;
                dataSeries3 = m_pDataSeries3_2SEC;
                break;
            case CHART_MODE_10_SECONDS:
                dataSeries1 = m_pDataSeries1_10SEC;
                dataSeries2 = m_pDataSeries2_10SEC;
                dataSeries3 = m_pDataSeries3_10SEC;
                break;
            case CHART_MODE_30_SECONDS:
                dataSeries1 = m_pDataSeries1_30SEC;
                dataSeries2 = m_pDataSeries2_30SEC;
                dataSeries3 = m_pDataSeries3_30SEC;
                break;
            case CHART_MODE_5_MINUTES:
                dataSeries1 = m_pDataSeries1_5MIN;
                dataSeries2 = m_pDataSeries2_5MIN;
                dataSeries3 = m_pDataSeries3_5MIN;
                break;
            default:
                dataSeries1 = m_pDataSeries1_2SEC;
                dataSeries2 = m_pDataSeries2_2SEC;
                dataSeries3 = m_pDataSeries3_2SEC;
                break;
        }

        switch (dataSeriesNumber) {
            case 1:
                if (mToPlotDataSeries1 ^ status) {  // mToPlotDataSeries1 XOR status = 1
                    if (status == false) {
                        dataSeries1.getNode().setStyle("-fx-stroke: " + COLOR_TRANSPARENT + ";");
                    } else {
                        dataSeries1.getNode().setStyle("-fx-stroke: " + COLOR_DATA_SERIES_1 + ";");
                    }
                    mToPlotDataSeries1 = status;
                }
                break;
            case 2:
                if (mToPlotDataSeries2 ^ status) {  // mToPlotDataSeries2 XOR status = 1
                    if (status == false) {
                        dataSeries2.getNode().setStyle("-fx-stroke: " + COLOR_TRANSPARENT + ";");
                    } else {
                        dataSeries2.getNode().setStyle("-fx-stroke: " + COLOR_DATA_SERIES_2 + ";");
                    }
                    mToPlotDataSeries2 = status;
                }
                break;
            case 3:
                if (mToPlotDataSeries3 ^ status) {  // mToPlotDataSeries3 XOR status = 1
                    if (status == false) {
                        dataSeries3.getNode().setStyle("-fx-stroke: " + COLOR_TRANSPARENT + ";");
                    } else {
                        dataSeries3.getNode().setStyle("-fx-stroke: " + COLOR_DATA_SERIES_3 + ";");
                    }
                    mToPlotDataSeries3 = status;
                }
                break;
            default:
                break;
        }
    }

    public void changeChartMode(int chartMode) throws ChartModeException {
        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
            case CHART_MODE_10_SECONDS:
            case CHART_MODE_30_SECONDS:
            case CHART_MODE_5_MINUTES:
                try {
                    m_pLock.lock();
                    mChartMode = chartMode;
                } finally {
                    m_pLock.unlock();
                }

                addOrReplaceDataSeries();

                break;
            default:
                throw new ChartModeException("The chart mode is not acceptable");
        }
    }

    /**
     * Add data to data series and display on the graph
     *
     * @param timeInSecond The time in second
     * @param value1
     * @param value2
     * @param value3
     */
    public void addToDataSeries(Long timeInSecond, Float value1, Float value2, Float value3) {
        try {
            m_pLock.lock();
            mTimeInSecond = timeInSecond;
            mPlotValue1 = value1;
            mPlotValue2 = value2;
            mPlotValue3 = value3;
        } finally {
            m_pLock.unlock();
        }
    }

    private void initDataSeries(String name1, String name2, String name3) {
        initDataSeries1(name1);
        initDataSeries2(name2);
        initDataSeries3(name3);
    }

    /**
     * Add or replace the current data series with a new one due to the change
     * of the chart mode.
     */
    private void addOrReplaceDataSeries() {
        int chartMode;
        try {
            m_pLock.lock();
            chartMode = mChartMode;
        } finally {
            m_pLock.unlock();
        }

        if (m_pLineChart.getData().size() > 0) {
            m_pLineChart.getData().clear();
        }

        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
                m_pLineChart.getData().add(m_pDataSeries1_2SEC);
                m_pLineChart.getData().add(m_pDataSeries2_2SEC);
                m_pLineChart.getData().add(m_pDataSeries3_2SEC);
                break;
            case CHART_MODE_10_SECONDS:
                m_pLineChart.getData().add(m_pDataSeries1_10SEC);
                m_pLineChart.getData().add(m_pDataSeries2_10SEC);
                m_pLineChart.getData().add(m_pDataSeries3_10SEC);
                break;
            case CHART_MODE_30_SECONDS:
                m_pLineChart.getData().add(m_pDataSeries1_30SEC);
                m_pLineChart.getData().add(m_pDataSeries2_30SEC);
                m_pLineChart.getData().add(m_pDataSeries3_30SEC);
                break;
            case CHART_MODE_5_MINUTES:
                m_pLineChart.getData().add(m_pDataSeries1_5MIN);
                m_pLineChart.getData().add(m_pDataSeries2_5MIN);
                m_pLineChart.getData().add(m_pDataSeries3_5MIN);
                break;
            default:
                break;
        }

        m_pXAxis.setDisable(true);
        int chartRange;
        int tickUnit;
        ObservableList<XYChart.Data<Number, Number>> observableList1, observableList2, observableList3;
        switch (mChartMode) {
            case CHART_MODE_2_SECONDS:
                observableList1 = m_pDataSerieObservableList1_2SEC;
                observableList2 = m_pDataSerieObservableList2_2SEC;
                observableList3 = m_pDataSerieObservableList3_2SEC;
                chartRange = X_AXIS_RANGE_2_SECOND_MODE;
                tickUnit = X_AXIS_TICK_2_SECOND_MODE;
                break;
            case CHART_MODE_10_SECONDS:
                observableList1 = m_pDataSerieObservableList1_10SEC;
                observableList2 = m_pDataSerieObservableList2_10SEC;
                observableList3 = m_pDataSerieObservableList3_10SEC;
                chartRange = X_AXIS_RANGE_10_SECOND_MODE;
                tickUnit = X_AXIS_TICK_10_SECOND_MODE;
                break;
            case CHART_MODE_30_SECONDS:
                observableList1 = m_pDataSerieObservableList1_30SEC;
                observableList2 = m_pDataSerieObservableList2_30SEC;
                observableList3 = m_pDataSerieObservableList3_30SEC;
                chartRange = X_AXIS_RANGE_30_SECOND_MODE;
                tickUnit = X_AXIS_TICK_30_SECOND_MODE;
                break;
            case CHART_MODE_5_MINUTES:
                observableList1 = m_pDataSerieObservableList1_5MIN;
                observableList2 = m_pDataSerieObservableList2_5MIN;
                observableList3 = m_pDataSerieObservableList3_5MIN;
                chartRange = X_AXIS_RANGE_5_MINUTE_MODE;
                tickUnit = X_AXIS_TICK_5_MINUTE_MODE;
                break;
            default:
                observableList1 = m_pDataSerieObservableList1_2SEC;
                observableList2 = m_pDataSerieObservableList2_2SEC;
                observableList3 = m_pDataSerieObservableList3_2SEC;
                chartRange = X_AXIS_RANGE_2_SECOND_MODE;
                tickUnit = X_AXIS_TICK_2_SECOND_MODE;
                break;
        }

        if (observableList1.isEmpty() == false && observableList2.isEmpty() == false && observableList3.isEmpty() == false) {
            double maxValue = Math.max(Math.max(observableList1.get(observableList1.size() - 1).getXValue().doubleValue(),
                    observableList2.get(observableList1.size() - 1).getXValue().doubleValue()),
                    observableList3.get(observableList1.size() - 1).getXValue().doubleValue());
            double newLowerBound = maxValue - chartRange;
            double newUpperBound = maxValue;
            m_pXAxis.setLowerBound(newLowerBound);
            m_pXAxis.setUpperBound(newUpperBound);
            m_pXAxis.setTickUnit(tickUnit);
            m_pXAxis.setDisable(false);
        }
        
        // Update if there is some data series which is not to be plotted
        boolean toPlotDataSeries1 = mToPlotDataSeries1;
        boolean toPlotDataSeries2 = mToPlotDataSeries2;
        boolean toPlotDataSeries3 = mToPlotDataSeries3;
        mToPlotDataSeries1 = true;
        mToPlotDataSeries2 = true;
        mToPlotDataSeries3 = true;        
        switchDataSeriesStatus(1, toPlotDataSeries1);
        switchDataSeriesStatus(2, toPlotDataSeries2);
        switchDataSeriesStatus(3, toPlotDataSeries3);
        

    }

    private void initDataSeries1(String name) {
        m_pDataSeries1_2SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList1_2SEC = m_pDataSeries1_2SEC.getData();
        if (name != null) {
            m_pDataSeries1_2SEC.setName(name);
        }

        m_pDataSeries1_10SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList1_10SEC = m_pDataSeries1_10SEC.getData();
        if (name != null) {
            m_pDataSeries1_10SEC.setName(name);
        }

        m_pDataSeries1_30SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList1_30SEC = m_pDataSeries1_30SEC.getData();
        if (name != null) {
            m_pDataSeries1_30SEC.setName(name);
        }

        m_pDataSeries1_5MIN = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList1_5MIN = m_pDataSeries1_5MIN.getData();
        if (name != null) {
            m_pDataSeries1_5MIN.setName(name);
        }
    }

    private void addToDataSeries1(Long timeInSecond, Float value, int chartMode) {
        ObservableList<XYChart.Data<Number, Number>> observableList;
        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
                observableList = m_pDataSerieObservableList1_2SEC;
                break;
            case CHART_MODE_10_SECONDS:
                observableList = m_pDataSerieObservableList1_10SEC;
                break;
            case CHART_MODE_30_SECONDS:
                observableList = m_pDataSerieObservableList1_30SEC;
                break;
            case CHART_MODE_5_MINUTES:
                observableList = m_pDataSerieObservableList1_5MIN;
                break;
            default:
                observableList = m_pDataSerieObservableList1_2SEC;
                break;
        }

        if (timeInSecond != null && value != null) {
            observableList.add(new XYChart.Data<Number, Number>(timeInSecond, value));
            if (observableList.size() > HISTORY_SIZE + 1) {
                observableList.remove(0);
            }
        }
    }

    private void initDataSeries2(String name) {
        m_pDataSeries2_2SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList2_2SEC = m_pDataSeries2_2SEC.getData();
        if (name != null) {
            m_pDataSeries2_2SEC.setName(name);
        }

        m_pDataSeries2_10SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList2_10SEC = m_pDataSeries2_10SEC.getData();
        if (name != null) {
            m_pDataSeries2_10SEC.setName(name);
        }

        m_pDataSeries2_30SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList2_30SEC = m_pDataSeries2_30SEC.getData();
        if (name != null) {
            m_pDataSeries2_30SEC.setName(name);
        }

        m_pDataSeries2_5MIN = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList2_5MIN = m_pDataSeries2_5MIN.getData();
        if (name != null) {
            m_pDataSeries2_5MIN.setName(name);
        }
    }

    private void addToDataSeries2(Long timeInSecond, Float value, int chartMode) {
        ObservableList<XYChart.Data<Number, Number>> observableList;
        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
                observableList = m_pDataSerieObservableList2_2SEC;
                break;
            case CHART_MODE_10_SECONDS:
                observableList = m_pDataSerieObservableList2_10SEC;
                break;
            case CHART_MODE_30_SECONDS:
                observableList = m_pDataSerieObservableList2_30SEC;
                break;
            case CHART_MODE_5_MINUTES:
                observableList = m_pDataSerieObservableList2_5MIN;
                break;
            default:
                observableList = m_pDataSerieObservableList2_2SEC;
                break;
        }

        if (timeInSecond != null && value != null) {
            observableList.add(new XYChart.Data<Number, Number>(timeInSecond, value));
            if (observableList.size() > HISTORY_SIZE + 1) {
                observableList.remove(0);
            }
        }
    }

    private void initDataSeries3(String name) {
        m_pDataSeries3_2SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList3_2SEC = m_pDataSeries3_2SEC.getData();
        if (name != null) {
            m_pDataSeries3_2SEC.setName(name);
        }

        m_pDataSeries3_10SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList3_10SEC = m_pDataSeries3_10SEC.getData();
        if (name != null) {
            m_pDataSeries3_10SEC.setName(name);
        }

        m_pDataSeries3_30SEC = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList3_30SEC = m_pDataSeries3_30SEC.getData();
        if (name != null) {
            m_pDataSeries3_30SEC.setName(name);
        }

        m_pDataSeries3_5MIN = new XYChart.Series<Number, Number>();
        m_pDataSerieObservableList3_5MIN = m_pDataSeries3_5MIN.getData();
        if (name != null) {
            m_pDataSeries3_5MIN.setName(name);
        }
    }

    private void addToDataSeries3(Long timeInSecond, Float value, int chartMode) {
        ObservableList<XYChart.Data<Number, Number>> observableList;
        switch (chartMode) {
            case CHART_MODE_2_SECONDS:
                observableList = m_pDataSerieObservableList3_2SEC;
                break;
            case CHART_MODE_10_SECONDS:
                observableList = m_pDataSerieObservableList3_10SEC;
                break;
            case CHART_MODE_30_SECONDS:
                observableList = m_pDataSerieObservableList3_30SEC;
                break;
            case CHART_MODE_5_MINUTES:
                observableList = m_pDataSerieObservableList3_5MIN;
                break;
            default:
                observableList = m_pDataSerieObservableList3_2SEC;
                break;
        }

        if (timeInSecond != null && value != null) {
            observableList.add(new XYChart.Data<Number, Number>(timeInSecond, value));
            if (observableList.size() > HISTORY_SIZE + 1) {
                observableList.remove(0);
            }
        }
    }

    private void createChart(String lineChartTitle) {
        try {
            m_pXAxis = new NumberAxis(0, X_AXIS_RANGE_2_SECOND_MODE, X_AXIS_TICK_2_SECOND_MODE);
            m_pXAxis.setDisable(true);
            m_pYAxis = new NumberAxis();
            m_pYAxis.setAutoRanging(true);

            m_pLineChart = new LineChart<Number, Number>(m_pXAxis, m_pYAxis);
            m_pLineChart.setCreateSymbols(false);
            m_pLineChart.setAnimated(false);
            m_pLineChart.setLegendVisible(false);
            m_pLineChart.setTitle(lineChartTitle);

            m_pXAxis.setLabel("Time");
            m_pXAxis.setForceZeroInRange(false);
            m_pYAxis.setLabel("Value");
            m_pLock.lock();
        } finally {
            m_pLock.unlock();
        }

    }
}