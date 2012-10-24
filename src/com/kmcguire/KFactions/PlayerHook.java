/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author kmcguire
 */
public class PlayerHook implements Listener {
    P               p;
    PlayerHook(P p) {
        this.p = p;
    }
    
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        synchronized(p) {
            p.handlePlayerRespawnEvent(event);
        }
    }
    
    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        synchronized(p) {
            p.handlePlayerLogin(event);
        }
    }
    
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        synchronized(p) {
            p.handlePlayerMove(event);
        }
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        synchronized(p) {
            p.handlePlayerInteract(event);
        }
    }
    
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        synchronized(p) {
            p.handleEntityDamageEntity(event);
        }
    }
}
