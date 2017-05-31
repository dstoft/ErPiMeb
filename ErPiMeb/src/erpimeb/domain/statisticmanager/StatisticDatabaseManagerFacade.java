/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.statisticmanager;

import java.util.List;

/**
 *
 * @author chris
 */
public interface StatisticDatabaseManagerFacade {
    public abstract List<Long> getOrderTimestamps(String status, long since);
    
}
