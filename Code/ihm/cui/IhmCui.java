package PilierDeLaTerre.ihm.cui;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;
import PilierDeLaTerre.metier.Pilier;

import java.util.ArrayList;

public class IhmCui
{
    private int              nbDallePresente;
    private ArrayList<Dalle> dalles;

    public IhmCui()
    {
        this.nbDallePresente = 0;
        this.dalles          = new ArrayList<Dalle>();
    }

    public char PilierToChar(Pilier p)
    {
        if(p == null){return ' ';}
        return p.getCoul();
    }

    public IhmCui(Parterre part)
    {
        this.nbDallePresente = part.getGrilleDalles().size();
        this.dalles          = part.getGrilleDalles();
    }

    public boolean ajouterDalle(Dalle d)
    {
        if(nbDallePresente == 16){return false;}
            dalles.add(d);
        this.nbDallePresente++;
        return true;
    }

    public String getLiasonsPilier()
    {
         /*affichage des pillier*/
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

    public String getLiasonsDalle()
    {
        /* affichage des liaison entre dalles*/

        String sRep =   "          +-----------------------+\n"+
                        "          |         Lié à         |\n"+
                        "          +---+---+---+---+---+---+\n"+
                        "          | 0 | 1 | 2 | 3 | 4 | 5 |\n"+
                        "+---------+---+---+---+---+---+---+\n";
        for(Dalle d: dalles)
        {
            if(d != null)
            {
                sRep   +="| Dalle " + d.getNom()  + " | "                       + this.DalleToChar(d.getDalleAdjacent(0)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(1))  + " | " + this.DalleToChar(d.getDalleAdjacent(2)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(3))  + " | " + this.DalleToChar(d.getDalleAdjacent(4)) +
                        " | "+ this.DalleToChar(d.getDalleAdjacent(5))  + " |\n"+
                        "+---------+---+---+---+---+---+---+\n";
            }
        }
        return sRep;
    }

    public char DalleToChar(Dalle d)
    {
        if (d == null) {return ' ';}
        return d.getNom();
    }

    public static void main(String[] args)
    {
        Parterre part = new Parterre();
        IhmCui tab2 = new IhmCui(part);
        System.out.println(tab2);
    }
    }