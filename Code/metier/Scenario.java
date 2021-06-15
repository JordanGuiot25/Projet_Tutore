package Equipe_22.metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

import Equipe_22.metier.Joueur;
import Equipe_22.metier.Dalle;
import Equipe_22.metier.Parterre;

import iut.algo.Decomposeur;

public class Scenario 
{
    public static Parterre getScenario(int numScenario)
    {
        ArrayList <Dalle> plateau    = new ArrayList<Dalle>(); // Plateau
        Joueur            joueur1    = new Joueur(1, 'M');           // Joueur 1
        Joueur            joueur2    = new Joueur(2, 'G');           // Joueur 2
        int               numeroTour = 0;                      // Numero du Tour

        for(int i = 0 ; i < 16; i++)
        {
            plateau.add(new Dalle());
        }


        try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "../Scenario/Scenario" + numScenario + ".txt" ) );

            /*---------------------------------------*/
            /*   Recuperation de la partie plateau   */
            String ligne = sc.nextLine();
            ligne = sc.nextLine();

            Decomposeur dec = new Decomposeur (ligne);
            plateau.get(0).setCoordonner(dec.getInt(0),dec.getInt(1));

            ligne = sc.nextLine();
            while(!ligne.equals("//Pilier"))
            {
                char  nomD1 = ligne.charAt(0);
                char  nomD2 = ligne.charAt(1);
                int   coteAdjacent = Integer.parseInt(ligne.substring(2));
                Dalle dalle1 = null;
                Dalle dalle2 = null;

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
            ligne = sc.nextLine();
            while(!ligne.equals("//Joueur"))
            {
                for(Dalle dalle : plateau)
                {
                    if( dalle.getNom() == ligne.charAt(0) )
                    {
                        dalle.rajoutPilier( ligne.charAt(1), Integer.parseInt( ligne.substring(2) ) );
                    }
                }

                ligne = sc.nextLine();
            }

            /*---------------------------------------*/
            /*   Recuperation de la partie Joueur    */
            while(!ligne.equals("//Tour"))
            {
                if( ligne.substring(0, 2).equals("J1") )
                {
                    if(ligne.length() > 4)
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
                            {
                                joueur1.ajouterDalles(dalle);
                                dalle  .setProprietaire(joueur1);
                            }
                                
                        }
                    }
                }
                else if ( ligne.substring(0, 2).equals("J2") )
                {
                    
                    if(ligne.length() > 4)
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
                            {
                                joueur2.ajouterDalles  (dalle);
                                dalle  .setProprietaire(joueur2);
                            }
                        }
                    }
                }
                ligne = sc.nextLine();
            }

            /*---------------------------------------*/
            /*   Recuperation de la partie Tour      */

            ligne = sc.nextLine();
            numeroTour = Integer.parseInt( ligne );

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }


        return new Parterre(plateau,joueur1,joueur2,numeroTour);
    }

}
