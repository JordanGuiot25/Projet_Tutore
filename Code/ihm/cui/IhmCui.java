
package Equipe_22.ihm.cui;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Parterre;
import Equipe_22.metier.Pilier;

import java.util.ArrayList;
/**
 * cette classe gere l'affichage du plateau sous forme de tableau dans la console
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 */

public class IhmCui
{
    /**
     * l'ensemble des dalles
     */
    private ArrayList<Dalle> dalles         ;

    /**Constructeur de la classe qui prend en parametre un Parterre
	 * @param part {@link Parterre}*/
    public IhmCui(Parterre part)
    {
        this.dalles          = part.getGrilleDalles();
    }

    /**écrit dans la console l'état du parterre
     * @return {@link String}
     */
    public String toString()
    {
        return this.getLiasonsDalle() +"\n\n\n" + this.getLiasonsPilier();
    }

    /** empéche les erreur et renvois le nom du pilier
     * @return  {@link char  }
	 * @param p {@link Pilier}*/
    private char PilierToChar(Pilier p)
    {
        if(p == null){return ' ';}
        return p.getCoul();
    }
    
    /** empéche les erreur et renvois le nom de la dalle
     * @return  {@link char  }
	 * @param d {@link Dalle}*/
    private char DalleToChar(Dalle d)
    {
        if (d == null) {return ' ';}
        return d.getNom();
    }

    /** Renvois un tableau qui montre les dalle, leurs coordonées et a quelles dalles elles sont reliées
     * @return sRep {@link String  }
     */
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

    /** Renvois un tableau qui montre les dalle et quels piliers sonts présents a quel endroit de la dalle
     * @return sRep {@link String  } */
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