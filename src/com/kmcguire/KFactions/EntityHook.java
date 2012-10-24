/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityHook implements Listener {
    private P           p;
    
    public EntityHook(P p) {
        this.p = p;
    }
    
    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
        synchronized (p) {
            p.handleEntityExplodeEvent(event);
        }
    }
    
}
