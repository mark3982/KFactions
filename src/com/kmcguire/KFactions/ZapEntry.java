package com.kmcguire.KFactions;

import java.io.Serializable;

public class ZapEntry implements Serializable {
    static final long serialVersionUID = 2327305384822269929L;
    
    public Faction          from;
    public Faction          to;
    public double           amount;
    public long             timeStart;
    public long             timeTick;     // last tick (when amount was updated)
    public double           perTick;      // how much per tick
    public boolean          isFake;      
}

