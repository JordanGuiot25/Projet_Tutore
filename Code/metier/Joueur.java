package PilierDeLaTerre.metier;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

import java.util.ArrayList;

public class Joueur 
{
    private ArrayList<Dalle>   tabDalles;
    private ArrayList<Pilier>  tabPilier;

    private int  nbPilier;
    private int  numJoueur ;
    private char coulJoueur;

    public Joueur(int numJoueur, char coulJoueur)
    {
        this.numJoueur = numJoueur;
        this.coulJoueur = coulJoueur;
        this.nbPilier = 24;

    }

    public void poserPilier()
    {
        if(nbPilier > 0)
        {
            this.nbPilier--;
        }
    }

    public void ajouterDalles(Dalle dalle)
    {
        this.tabDalles.add(dalle);
    }
}
