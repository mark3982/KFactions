package com.kmcguire.KFactions;

import java.io.Serializable;

public class WorldAnchorLocation implements Serializable {
    static final long serialVersionUID = 127229385804260928L;
    
    public WorldAnchorLocation(int x, int y, int z, String w, String byWho) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.byWho = byWho;
        timePlaced = System.currentTimeMillis();
    }
    
    public WorldAnchorLocation() {
        
    }
    
    public int          x;
    public int          y;
    public int          z;
    public String       w;
    public String       byWho;
    public long         timePlaced;
}
