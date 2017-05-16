/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.domain.mediamanager;

/**
 * Out of scope
 * @author AKT
 */
public class MediaManager implements MediaManagerFacade{
    public static MediaManager manager;
    
    public static MediaManager getInstance(){
        if(manager == null){
            manager = new MediaManager();
        }
        return manager;
    }
}
