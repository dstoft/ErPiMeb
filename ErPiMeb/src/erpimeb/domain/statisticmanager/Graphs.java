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
public class Graphs {
    
    Map<Integer, Integer> data;
    String xAxis, yAxis, title, seriesName;
    
    public Graphs(long creationTime, String xAxis, String yAxis, String title, String seriesName) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.title = title;
        this.seriesName = seriesName;
        
        this.data = new LinkedHashMap<>();
        //Only 28 to avoid duplicates in the HashMap
        for(long i = 0; i <= 28; i++) {
            data.put(this.getDayOfMonth(creationTime - (i * 86400000)), 0);
        }
    }
    
    public void addData(List<Long> data) {
        for(Long dataPoint : data) {
            int dayOfMonth = this.getDayOfMonth(dataPoint);
            this.data.put(dayOfMonth, this.data.get(dayOfMonth));
        }
    }
    
    private int getDayOfMonth(long milliSeconds) {
        Date currentDate = new Date(milliSeconds);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
