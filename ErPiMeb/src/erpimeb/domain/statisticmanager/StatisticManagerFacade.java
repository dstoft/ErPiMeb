/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.statisticmanager;

/**
 *
 * @author chris
 */
public interface StatisticManagerFacade {
    
    public abstract Graphs getCompletedOrders(long since);
    
}
