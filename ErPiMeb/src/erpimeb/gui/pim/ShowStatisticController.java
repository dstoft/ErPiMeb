/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.statisticmanager.Graph;
import erpimeb.domain.statisticmanager.StatisticManager;
import erpimeb.domain.statisticmanager.StatisticManagerFacade;
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DanielToft
 */
public class ShowStatisticController implements Initializable, Switchable {
    private StatisticManagerFacade smf;
    @FXML
    private AnchorPane lineChartAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.smf = StatisticManager.getInstance();
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }
    
    private void createLineChart(Graph graphs) {
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(graphs.getxAxis());
        yAxis.setLabel(graphs.getyAxis());
        
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle(graphs.getTitle());
        XYChart.Series series = new XYChart.Series();
        series.setName(graphs.getSeriesName());
        
        Map<Integer, Integer> data = graphs.getData();
        
        for(Integer integer : data.keySet()) {
            series.getData().add(new XYChart.Data("" + integer, data.get(integer)));
        }
        
        lineChart.getData().add(series);
        
        this.lineChartAP.getChildren().add(lineChart);
    }

    @Override
    public void setupInternals() {
        
    }

    @FXML
    private void handleShowStatistic(ActionEvent event) {
        this.createLineChart(this.smf.getCompletedOrders(System.currentTimeMillis() - (28 * 86400000L)));
    }
}
