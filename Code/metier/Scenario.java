package PilierDeLaTerre.metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

import PilierDeLaTerre.metier.Joueur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;

import iut.algo.Decomposeur;

public class Scenario 
{
    public static Parterre getScenario(int numScenario)
    {
        ArrayList <Dalle> plateau    = new ArrayList<Dalle>(); // Plateau
        Joueur            joueur1    = new Joueur();           // Joueur 1
        Joueur            joueur2    = new Joueur();           // Joueur 2
        int               numeroTour = 0;                      // Numero du Tour

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

                    ligne = sc.nextLine();
                }
                /*---------------------------------------*/
                /*   Recuperation de la partie pilier    */
                while(!ligne.equals("//Joueur"))
                {
                    if( ligne.substring(0, 1).equals("J1") )
                    {
                        if(ligne.length() > 2)
                        {
                            dec = new Decomposeur(ligne);
                            joueur1.setNbPilier      (dec.getInt(1) );
                            joueur1.setPilierDetruit (dec.getInt(2) );
                        }
                        else
                        {
                            for(Dalle dalle : plateau)
                            {
                                if(dalle.getNom() == ligne.charAt(2) )
                                    joueur1.ajouterDalles(dalle);
                            }
                        }
                    }
                    else if ( ligne.substring(0, 1).equals("J2") )
                    {
                        if(ligne.length() > 2)
                        {
                            dec = new Decomposeur(ligne);
                            joueur2.setNbPilier      (dec.getInt(1) );
                            joueur2.setPilierDetruit (dec.getInt(2) );
                        }
                        else
                        {
                            for(Dalle dalle : plateau)
                            {
                                if(dalle.getNom() == ligne.charAt(2) )
                                    joueur2.ajouterDalles(dalle);
                            }
                        }
                    }

                    ligne = sc.nextLine();
                }
                /*---------------------------------------*/
                /*   Recuperation de la partie Tour      */
                ligne = sc.nextLine();

                numeroTour = Integer.parseInt( ligne.substring(1) );
                

            }                

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }


        return new Parterre(plateau,joueur1,joueur2,numeroTour);
    }

}
