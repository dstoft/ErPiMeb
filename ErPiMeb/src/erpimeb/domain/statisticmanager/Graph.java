/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.statisticmanager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AKT
 */
public class Graph {
    
    Map<Integer, Integer> data;
    String xAxis, yAxis, title, seriesName;
    
    public Graph(long creationTime, String xAxis, String yAxis, String title, String seriesName) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.title = title;
        this.seriesName = seriesName;
        
        this.data = new LinkedHashMap<>();
        //Only 28 to avoid duplicates in the HashMap
        for(long i = 28; i >= 0; i--) {
            data.put(this.getDayOfMonth(creationTime - (i * 86400000)), 0);
        }
    }
    
    public void addData(List<Long> data) {
        for(Long dataPoint : data) {
            int dayOfMonth = this.getDayOfMonth(dataPoint);
            this.data.put(dayOfMonth, this.data.get(dayOfMonth) + 1);
        }
    }
    
    public Map<Integer, Integer> getData() {
        return this.data;
    }

    public String getxAxis() {
        return xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public String getTitle() {
        return title;
    }

    public String getSeriesName() {
        return seriesName;
    }
    
    private int getDayOfMonth(long milliSeconds) {
        Date currentDate = new Date(milliSeconds);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
