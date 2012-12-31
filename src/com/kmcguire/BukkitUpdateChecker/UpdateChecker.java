package com.kmcguire.BukkitUpdateChecker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;

public class UpdateChecker {
    public  long getCheckDelay() {
        return checkDelay;
    }

    public void setCheckDelay(long checkDelay) {
        this.checkDelay = checkDelay;
    }

    public Version getLatestVersion() {
        return latestVersion;
    }

    public Version getThisVersion() {
        return thisVersion;
    }
    
    private Version               thisVersion;   // this version

    public String getProjectName() {
        return projectName;
    }

    /**
     * This is your Bukkit project name. For example:
     * http://dev.bukkit.org/server-mods/kfactions/files/
     * 
     * Where kfactions is the project name. This method will
     * automatically convert the project name to lowercase.
     * 
     * @param projectName 
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    private Version                latestVersion; // latest version or null
    private long                   lastCheck;     // the last time we checked
    private Thread                 checkThread;
    private long                   checkDelay;
    private String                 projectName;
    
    private int makeInt(String a) {
        try {
            return Integer.parseInt(a);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public  void start() {
        if (!checkThread.isAlive()) {
            checkThread.setDaemon(true);
            checkThread.start();
        }
    }
    
    public UpdateChecker() {
        CodeSource          cs;
        String              src;
        
        cs = UpdateChecker.class.getProtectionDomain().getCodeSource();
        
        // <name>-[major].[minor].[revision][stage]
        
        src = cs.getLocation().toString();
        
        src = src.substring(src.lastIndexOf("-") + 1);
        thisVersion = new Version(src.substring(0, src.lastIndexOf(".")));
        
        latestVersion = null;
        checkThread = null;
        lastCheck = 0;
        checkDelay = 1000 * 60 * 30;

        checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (System.currentTimeMillis() - lastCheck < checkDelay) {
                        continue;
                    }
                    
                    lastCheck = System.currentTimeMillis();
                    
                    ///
                    URLConnection           conn;
                    String                  url;
                    ByteArrayOutputStream   bb;
                    byte[]                  bbuf;
                    InputStream             response;
                    int                     cnt;
                    String                  page;
                    String                  bmark;
                    int                     end;
                    String                  part;
                    
                    Version                 high;
                    Version                 cur;
                    
                    high = null;
                    
                    url = String.format("http://dev.bukkit.org/server-mods/%s/files/", projectName);
                    
                    try {
                        conn = new URL(url).openConnection();
                        conn.setDoOutput(false); // GET
                        response = conn.getInputStream();

                        bb = new ByteArrayOutputStream();
                        bbuf = new byte[1024];

                        while ((cnt = response.read(bbuf)) > -1) {
                            bb.write(bbuf, 0, cnt);
                        }
                        
                        page = bb.toString();
                        
                        //bmark = String.format("\"/server-mods/%s/files/", projectName);
                        //bmark = "\"http://dev.bukkit.org/";
                        bmark = "<td class=\"col-filename\">";
                        
                        for (cnt = page.indexOf(bmark); page.indexOf(bmark, cnt + 1) > -1; cnt = page.indexOf(bmark, cnt + 1)) {
                            end = page.indexOf("</td>", cnt + 1);
                            if (end < 0) {
                                continue;
                            }
                            
                            part = page.substring(cnt + bmark.length(), end);
                            part = part.toLowerCase();
                            part = part.trim();
                            
                            end = part.lastIndexOf(".");
                            
                            if (end < 0) {
                                continue;
                            }
                            
                            if (!part.substring(end).equals(".jar")) {
                                continue;
                            }
                            
                            part = part.substring(0, end);
                            
                            end = part.lastIndexOf("-");
                            
                            if (end < 0) {
                                continue;
                            }
                            
                            part = part.substring(end + 1);
                            
                            cur = new Version(part);
                            
                            if (high == null || cur.isHigherThan(high)) {
                                high = cur;
                            }
                            //
                        }
                        latestVersion = high;
                        // http://dev.bukkit.org/media/files/661/47/KFactions-1.18.41B.jar
                    } catch (MalformedURLException ex) {
                    } catch (IOException ex) {
                    }
                    ////
                    synchronized (this) {
                        try {
                            this.wait(5000);
                        } catch (InterruptedException ex) {
                        }
                    }
                    ////
                }
            }
        });     
    }
    
    
    /**
     * This shall be called from the Bukkit main thread and from no
     * other thread. This will determine if an updated version has
     * been released in which case it will notify the player, or if
     * it needs to contact a remote server it will delay notifying 
     * the player and instead create a new work thread to handle
     * the check.
     * 
     * @param playerName            the player's name (not display name)
     */
    public  boolean canUpdate() {
        if (latestVersion == null) {
            return false;
        }
        
        if (latestVersion.isHigherThan(thisVersion)) {
            return true;
        }
        
        return false;
    }
}
