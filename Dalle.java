public class Dalle
{
    private char nom;

    public Dalle(char nom)
    {
        this.nom = nom;
    }
    public char getNom()
    {
        return this.nom;
    }

    public char getAdjacent(int nb)
    {
        return ((char) ((int)(Math.random()*16)+64));
    }
}