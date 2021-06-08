package PilierDeLaTerre.Scenario;

import java.util.*;
import PilierDeLaTerre.*;

import iut.algo.Decomposeur;

public class Scenario 
{
    public Scenario()
    {

    }

    public static Parterre getScenario(int numScenario)
    {
        ArrayList <Dalle> plateau = new ArrayList<Dalle>();
        for(int i = 0 ; i < 16; i++)
        {
            plateau.add(new Dalle());
        }
        try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "entree.txt" ) );

			while ( sc.hasNextLine() )
            {
                /*---------------------------------------*/
                /*   Recuperation de la partie plateau   */
                String ligne = sc.nextLine();
                Decomposeur dec = new Decomposeur (ligne);
                plateau.get(0).setCoordonner(dec.getInt(0),dec.getInt(1));
                ligne = sc.nextLine();
                while(!ligne.equals("//Pilier"))
                {
                    char nomD1 = ligne.charAt(0);
                    char nomD2 = ligne.charAt(1);
                    int coteAdjacent = Integer.parseInt(ligne.substring(2));

                    for(Dalle dalle : plateau)
                    {
                        if(dalle.getNom() == nomD1)
                        {
                            dalle1 = dalle;
                        }
                        if(dalle.getNom() == nomD2)
                        {
                            dalle2 = dalle;
                        }
                    }

                    dalle1.setAdjacent(coteAdjacent, dalle2);
                }
                /*---------------------------------------*/
                /*   Recuperation de la partie pilier    */
                while(!ligne.equals("//Joueur"))
                {
                    if( ligne.substring(0, 1).equals("J1") )
                    {
                        
                    }
                }

            }                

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
}
