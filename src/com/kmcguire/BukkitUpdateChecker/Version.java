package com.kmcguire.BukkitUpdateChecker;

public class Version {
    private int          maj;
    private int          min;
    private int          rev;
    private String       tag;

    public int getMaj() {
        return maj;
    }

    public int getMin() {
        return min;
    }

    public int getRev() {
        return rev;
    }

    public String getTag() {
        return tag;
    }
    
    @Override
    public String toString() {
        return String.format("%d.%d.%d%s", maj, min, rev, tag);
    }
    
    public boolean isHigherThan(Version v) {
        if (maj < v.maj) {
            return false;
        }
        if (maj > v.maj) {
            return true;
        }
        
        if (min < v.min) {
            return false;
        }
        if (min > v.min) {
            return true;
        }
        
        if (rev < v.rev) {
            return false;
        }
        if (rev > v.rev) {
            return true;
        }
        
        return false;
    }
    
    public Version(String s) {
        int         x;
        int         e;
        int         c;
        String      part;
        
        maj = 0;
        min = 0;
        rev = 0;
        tag = "";
        
        try {
            x = s.indexOf(".");
            if (x < 0) {
                return;
            }
            maj = Integer.parseInt(s.substring(0, x));
            e = x + 1;

            x = s.indexOf(".", e);
            if (x < 0) {
                return;
            }
            min = Integer.parseInt(s.substring(e, x));
            e = x + 1;

            part = s.substring(e);
            
            for (c = 0; c < part.length(); ++c) {
                if (!Character.isDigit(part.charAt(c))) {
                    break;
                }
            }
            
            rev = Integer.parseInt(part.substring(0, c));
            if (c < part.length()) {
                tag = part.substring(c);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return;
        }
    }
}