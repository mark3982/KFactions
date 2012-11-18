/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Faction implements Serializable {
    public String                          name;
    public String                          desc;
    public Map<String, FactionPlayer>      players;
    //public Map<Long, FactionChunk>         chunks;
    public Map<String, Map<Long, FactionChunk>>     chunks;
    public int                             mri;         // minimum rank to invite/kick
    public int                             mrc;         // minimum rank to claim/declaim
    public Set<String>                     invites;     // invintations
    public int                             flags;
    public long                            lpud;        // last power update
    public double                          power;
    public Map<String, Integer>            friends;
    //
    public Set<String>                     allies;
    public Set<String>                     enemies;
    //
    public HashSet<ZapEntry>               zappersOutgoing;    
    public HashSet<ZapEntry>               zappersIncoming;
    public double                          mrz;         // minimum rank to zap
    public double                          mrtp;        // minimum rank to tptp
    public double                          hx, hy, hz;  // home coordinates
    public String                          hw;
    // 
    public HashSet<WorldAnchorLocation>    walocs;      // world anchor locations
    //
    public int                             mrsh;       // minimum rank to sethome
    //
    public long                            worthEMC;
    //
    public boolean                         peaceful;    // is faction peaceful
    
    static final long serialVersionUID = 6227009385804260928L;
    
    /** This constructor is still used when a faction is created using
     *  the /f create command, and it must still properly initialize fields
     *  similar to the YAML loading code when loading this data structure
     *  from disk.
     */
    Faction() {
        players = new HashMap<String, FactionPlayer>();
        chunks = new HashMap<String, Map<Long, FactionChunk>>();
        lpud = System.currentTimeMillis();
        invites = new HashSet<String>();
        friends = new HashMap<String, Integer>();
        zappersIncoming = new HashSet<ZapEntry>();
        zappersOutgoing = new HashSet<ZapEntry>();
        mrz = 900;
        mrtp = 900;
        mrsh = 900;
        hx = 0;
        hy = 0;
        hz = 0;
        hw = null;
        mri = 700;
        mrc = 600;
        walocs = new HashSet<WorldAnchorLocation>();
        allies = new HashSet<String>();
        enemies = new HashSet<String>();
    }

    /** This methods was primarily used when the old data format was used which
     *  used the Java binary serialization. And, if the plugin was updated to
     *  contain new fields then these had to be initialized from null, and this
     *  function was what was used to catch this situation. Now with the YAML
     *  format and the more hands on loading code this type of stuff can be done
     *  directly in that YAML load function.
     * 
     */
    public void initFromStorage() {
    }
}
