/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FactionChunk implements Serializable {
    public Faction              faction;
    public int                  mru;        // minimum rank usage (chest, doors, ..ect)
    public int                  mrb;        // minimum rank build (place & destroy blocks)
    public Set<String>          users;      // users can use things
    public Set<String>          builders;   // builders can build/destroy things    
    public int                  x, z;
    public boolean                          tiddefreject;
    public Map<Integer, Integer>         tid;
    public boolean                          tidudefreject;
    public Map<Integer, Integer>         tidu;
    public String               worldName;
    
    
    static final long serialVersionUID = -7603850029577747330L;
    
    FactionChunk() {
        users = new HashSet<String>();
        builders = new HashSet<String>();
        tid = new HashMap<Integer, Integer>();
        tidu = new HashMap<Integer, Integer>();
    }
}
