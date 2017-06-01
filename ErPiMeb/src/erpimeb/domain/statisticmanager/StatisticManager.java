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
    private static StatisticManager manager;
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
    public Graph getCompletedOrders(long since) {Graph returnGraphs = new Graph(System.currentTimeMillis(), "Dag på måneden", "Antal", "Gennemførte ordre", "Gennemførte ordre");
        returnGraphs.addData(this.dmf.getOrderTimestamps("complete", since));
        return returnGraphs;
    }
}
