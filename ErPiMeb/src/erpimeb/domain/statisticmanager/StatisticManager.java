/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.statisticmanager;

import erpimeb.persistence.databasemanager.DatabaseManager;

/**
 *
 * @author chris
 */
public class StatisticManager implements StatisticManagerFacade{
    public static StatisticManager manager;
    private Graphs graphs;
    private StatisticDatabaseManagerFacade dmf;
    
    public static StatisticManager getInstance(){
        if(manager == null){
            manager = new StatisticManager();
        }
        return manager;
    }
    
    private StatisticManager(){
        this.dmf = DatabaseManager.getInstance();
    }
    
    @Override
    public Graphs getCompletedOrders(long since) {Graphs returnGraphs = new Graphs(System.currentTimeMillis(), "Dag på måneden", "Antal", "Gennemførte ordre", "Gennemførte ordre");
        returnGraphs.addData(this.dmf.getOrderTimestamps("Completed", since));
        return returnGraphs;
    }
}
