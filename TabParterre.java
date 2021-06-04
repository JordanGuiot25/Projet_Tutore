public class TabParterre
{
    private int nbDalle;
    private int nbDallePresente;
    private Dalle[] dalles;
    public TabParterre(int nbDalle)
    {
        this.nbDallePresente = 0;
        this.nbDalle         = nbDalle;
        this.dalles          = new Dalle[this.nbDalle];
    }

    public boolean ajouterDalle(Dalle d)
    {
        if(nbDalle == nbDallePresente){return false;}
        dalles[nbDallePresente] = d;
        this.nbDallePresente++;
        return true;
    }

    public String toString()
    {
        String sRep ="          +-----------------------+\n"+
                     "          |         Lié à         |\n"+
                     "          +---+---+---+---+---+---+\n"+
                     "          | 0 | 1 | 2 | 3 | 4 | 5 |\n"+
                     "+---------+---+---+---+---+---+---+\n";
        for(Dalle d: dalles)
        {
            if(d!= null)
            {
                sRep   +="| Dalle " + d.getNom()  + " | " + d.getAdjacent(0) +
                        " | "+ d.getAdjacent(1)  + " | " + d.getAdjacent(2) +
                        " | "+ d.getAdjacent(3)  + " | " + d.getAdjacent(4) +
                        " | "+ d.getAdjacent(5)  + " |\n"+
                        "+---------+---+---+---+---+---+---+\n";
            }
        }
        return sRep;
    }

    public static void main(String[] args)
    {
        TabParterre tab1 = new TabParterre(3);
        Dalle d1 = new Dalle('a');
        tab1.ajouterDalle(d1);
        Dalle d2 = new Dalle('b');
        tab1.ajouterDalle(d2);
        Dalle d3 = new Dalle('c');
        tab1.ajouterDalle(d3); 
        System.out.println(tab1);

    }
}