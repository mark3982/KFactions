/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockHook implements Listener {
    P           p;
    BlockHook(P p) {
        this.p = p;
    }
    
    @EventHandler (priority = EventPriority.LOWEST)
    public void onBlockBreakEvent(BlockBreakEvent event) {
        synchronized(p) {
            p.handleBlockBreak(event);
        }
    }
    
    @EventHandler (priority = EventPriority.LOWEST)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        synchronized(p) {
            p.handleBlockPlace(event);
        }
    }
}
