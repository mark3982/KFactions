/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kmcguire.KFactions;

import java.io.Serializable;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class FactionPlayer implements Serializable {
    public Faction     faction;
    public String      name;
    public int         rank;
    
    static final long serialVersionUID = -7926870329073791367L;
}
