package PilierDeLaTerre.metier;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

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
        this.listeDalles.add(dalle);
    }

    public int  getNumJoueur()       { return this.numJoueur;       }
    public int  getNbPilier ()       { return this.nbPilier;        }
    public int  getNbPilierDetruis() { return this.nbPilierDetruis; }
    public char getCouleur()         { return this.coulJoueur;      }

    public ArrayList<Dalle>  getListeDalles() { return this.listeDalles; }
    public ArrayList<Pilier> getListePilier() { return this.listePilier; }
}
