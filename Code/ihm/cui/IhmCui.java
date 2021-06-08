package PilierDeLaTerre.ihm.cui;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;

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

    public IhmCui(Parterre part)
    {
        this.nbDallePresente = part.getDalles().size();
        this.dalles          = part.getDalles();
    }

    public boolean ajouterDalle(Dalle d)
    {
        if(nbDallePresente == 16){return false;}
            dalles.add(d);
        this.nbDallePresente++;
        return true;
    }

    public String toString()
    {
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