/**
 * @author Gaspard Gordien
 * 
 * 
 */


package PilierDeLaTerre.ihm.cui;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;
import PilierDeLaTerre.metier.Pilier;

import java.util.ArrayList;

public class IhmCui
{
    private int              nbDallePresente;
    private ArrayList<Dalle> dalles         ;

    public IhmCui(Parterre part)
    {
        this.nbDallePresente = part.getGrilleDalles().size();
        this.dalles          = part.getGrilleDalles();
    }

    /* renvois les deux tableaux */
    public String toString()
    {
        return this.getLiasonsDalle() +"\n\n\n" + this.getLiasonsPilier();
    }

    /* empéche les erreur et renvois le nom de la dalle */
    private char PilierToChar(Pilier p)
    {
        if(p == null){return ' ';}
        return p.getCoul();
    }
    
    /* empéche les erreur et renvois le nom du pilier */
    private char DalleToChar(Dalle d)
    {
        if (d == null) {return ' ';}
        return d.getNom();
    }

    /* Renvois un tableau qui montre les dalle, leurs coordonées et a quelles dalles elles sont reliées */
    private String getLiasonsDalle()
    {
        String sRep =   "          +-----------------------+-----------+\n"+
                        "          |         Lié à         | coordonées|\n"+
                        "          +---+---+---+---+---+---+-----------+\n"+
                        "          | 0 | 1 | 2 | 3 | 4 | 5 |  x  |  y  |\n"+
                        "+---------+---+---+---+---+---+---+-----+-----+\n";
        for(Dalle d: dalles)
        {
            if(d != null)
            {
                sRep   +="| Dalle " + d.getNom()  + " | "                       + this.DalleToChar(d.getDalleAdjacent(0)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(1))  + " | " + this.DalleToChar(d.getDalleAdjacent(2)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(3))  + " | " + this.DalleToChar(d.getDalleAdjacent(4)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(5))  + " |" + String.format("%5d",d.getX())+"|" +String.format("%5d",d.getY())+   "|\n"+
                        "+---------+---+---+---+---+---+---+-----+-----+\n";
            }
        }
        return sRep;
    }

    /* Renvois un tableau qui montre les dalle et quels piliers sonts présents a quel endroit de la dalle */
    private String getLiasonsPilier()
    {
        String sRep =   "\n\n          +-----------------------+\n"+
                            "          |   Pillier present     |\n"+
                            "          +---+---+---+---+---+---+\n"+
                            "          | 0 | 1 | 2 | 3 | 4 | 5 |\n"+
                            "+---------+---+---+---+---+---+---+\n";
        for(Dalle d: dalles)
        {
            Pilier[] lstPil = d.getSommets();
            if(d != null)
            {
                sRep   +="| Dalle " + d.getNom()  + " | "             + this.PilierToChar(lstPil[0]) +
                        " | "+ this.PilierToChar(lstPil[1])  + " | " + this.PilierToChar(lstPil[2]) +
                        " | "+ this.PilierToChar(lstPil[3])  + " | " + this.PilierToChar(lstPil[4]) +
                        " | "+ this.PilierToChar(lstPil[5])  + " |\n"+
                        "+---------+---+---+---+---+---+---+\n";
            }
        }
        return sRep;
    }

}