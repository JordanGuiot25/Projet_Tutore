package Equipe_22.metier;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Pilier;

import java.util.ArrayList;

public class Joueur 
{
    private ArrayList<Dalle>   listeDalles;
    private ArrayList<Pilier>  listePilier;

    private int  numJoueur ;
    private char coulJoueur;
    private int  nbPilier;
    private int  nbPilierDetruis;

    public Joueur(int numJoueur, char coulJoueur)
    {
        this.numJoueur       = numJoueur;
        this.coulJoueur      = coulJoueur;
        this.nbPilier        = 24;
        this.nbPilierDetruis = 0;
        this.listeDalles = new ArrayList<Dalle> ();
        this.listePilier = new ArrayList<Pilier>();
    }

    public boolean poserPilier()
    {
        if(nbPilier > 0)
        {
            this.nbPilier--;
            return true;
        }

        return false;
    }

    public void ajouterDalles(Dalle dalle)
    {
        boolean estDejaAjouter = false;
        for (Dalle dalleTmp : this.listeDalles )
        {
            if( dalleTmp == dalle )
                estDejaAjouter = true;
        }

        if ( !estDejaAjouter )
            this.listeDalles.add(dalle);
    }
    public void retirerDalle(Dalle dalle)
    {
        this.listeDalles.remove(dalle);
    }


    public void incrementationNbPilier(int incrementation)
    {
        this.nbPilier += incrementation;
    }
    public void incrementationNbPilierDetruis(int incrementation)
    {
        this.nbPilierDetruis += incrementation;
    }

    public void setNbPilier(int nbPilier)
    {
        this.nbPilier = nbPilier;
    }
    public void setPilierDetruit(int nbPilierDetruis)
    {
        this.nbPilierDetruis = nbPilierDetruis;
    }

    public int  getNumJoueur()       { return this.numJoueur;       }
    public int  getNbPilier ()       { return this.nbPilier;        }
    public int  getNbPilierDetruis() { return this.nbPilierDetruis; }
    public char getCouleur()         { return this.coulJoueur;      }

    public ArrayList<Dalle>  getListeDalles() { return this.listeDalles; }
    public ArrayList<Pilier> getListePilier() { return this.listePilier; }
}
