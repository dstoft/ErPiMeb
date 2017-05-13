/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatisticManager;

/**
 *
 * @author chris
 */
public class StatisticManager implements Facade{
    public static StatisticManager manager;
    private Graphs graphs;
    
    public static StatisticManager getInstance(){
        if(manager == null){
            manager = new StatisticManager();
        }
        return manager;
    }
    
}
