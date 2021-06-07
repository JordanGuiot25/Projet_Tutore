public class Joueur 
{
    Arraylist <Dalle >  tabDalles;
    Arraylist <Pilier>  tabPilier;

    private int nbPilier;
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
