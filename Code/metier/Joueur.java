package Equipe_22.metier;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Pilier;

import java.util.ArrayList;

public class Joueur 
{
    /** Liste des dalles que le joueur possède
     * @see listeDalles {@link Arraylist<Dalle> } 
     */
    private ArrayList<Dalle>   listeDalles;

    /** Numero du joueur
     * @see numJoueur {@link Int}
     */
    private int  numJoueur ;

    /** Couleur du joueur
     * @see coulJoueur {@link Char}
     */
    private char coulJoueur;

    /** Nombre de pilier restant au joueur
     * @see nbPilier {@link Int}
     */
    private int  nbPilier;

    /** Nombre de pilier détruit par le joueur
     * @see nbPilierDetruis {@link Int}
     */
    private int  nbPilierDetruis;


    /**
     * Constructeur de la class Joueur
     * @param numJoueur
     * @param coulJoueur
     */
    public Joueur(int numJoueur, char coulJoueur)
    {
        this.numJoueur       = numJoueur;
        this.coulJoueur      = coulJoueur;
        this.nbPilier        = 24;
        this.nbPilierDetruis = 0;
        this.listeDalles = new ArrayList<Dalle> ();
    }

    /**
     * Decremente le nombre de pilier restant 
     * @return validation {@link Boolean }
     */
    public boolean poserPilier()
    {
        if(nbPilier > 0)
        {
            this.nbPilier--;
            return true;
        }

        return false;
    }

    /**
     * Ajoute une dalle dans l'ensemble de dalle du Joueur
     * @param dalle {@link Dalle }
     */
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

    /**
     * Retire une dalle dans la liste du joueur
     * @param dalle {@link Dalle }
     */
    public void retirerDalle(Dalle dalle)
    {
        this.listeDalles.remove(dalle);
    }

    /**
     * Rajoute un certain nombre au nombre de pilier restant
     * @param incrementation {@link Int }
     */
    public void incrementationNbPilier(int incrementation)
    {
        this.nbPilier += incrementation;
    }

    /**
     * Rajoute un certain nombre au nombre de pilier détruis
     * @param incrementation {@link Int }
     */
    public void incrementationNbPilierDetruis(int incrementation)
    {
        this.nbPilierDetruis += incrementation;
    }

    /**
     * Définie à une valeur le nombre de pilier restant
     * @param nbPilier
     */
    public void setNbPilier(int nbPilier)
    {
        this.nbPilier = nbPilier;
    }

    /**
     * Définie à une valeur le nombre de pilier détruit
     * @param nbPilier
     */
    public void setPilierDetruit(int nbPilierDetruis)
    {
        this.nbPilierDetruis = nbPilierDetruis;
    }

    /**
     * Retourne le numéro du joueur
     * @return numJoueur {@link Int}
     */
    public int  getNumJoueur()       { return this.numJoueur;       }

    /**
     * Retourne le nombre de pilier restant
     * @return nbPilier {@link Int}
     */
    public int  getNbPilier ()       { return this.nbPilier;        }

    /**
     * Retourne le nombre de pilier detruis
     * @return nbPilierDetruis {@link Int}
     */
    public int  getNbPilierDetruis() { return this.nbPilierDetruis; }

    /**
     * Retourne la couleur du joueur
     * @return coulJoueur {@link Char }
     */
    public char getCouleur()         { return this.coulJoueur;      }

    /**
     * Retourne la liste des dalles du joueur
     * @return listeDalles {@link ArrayList<Dalle> }
     */
    public ArrayList<Dalle>  getListeDalles() { return this.listeDalles; }
}
