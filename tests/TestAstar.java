package tests;

import java.io.File;
import java.net.URL;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionNonScorable;
import exceptions.ExceptionObjectToCoordsTImpossible;
import models.Astar;
import models.Carte;
import models.Chemin;
import models.ExceptionArriveeInatteignable;

/**
 * Test de l'algorithme Astar sur les cartes
 * @author Luc
 *
 */
public class TestAstar {
	
	//Dossier contenant les cartes
	final static String repoCartes =  System.getProperty("user.dir") + "/src/cartes/";
	
	public static void main (String[] args) throws ExceptionFichierMalforme, ExceptionFichierInexistant, ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable, ExceptionNonScorable {
		
		String racine = repoCartes;
		File[] mapFiles = {
			new File(racine + "map.txt"),
			new File(racine +"map2.txt"),
			new File(racine +"map3.txt"),
			new File(racine +"map4.txt"),
			new File(racine +"map5.txt"),
			new File(racine +"mapEchec.txt")
		};
		
		Carte[] cartes = new Carte[mapFiles.length];
		Carte[] savCartes = new Carte[mapFiles.length];
		for (int i = 0; i < mapFiles.length; i++) {
			cartes[i] = new Carte(mapFiles[i]);
			savCartes[i] = new Carte(mapFiles[i]);
		}

		Chemin[] chemins = new Chemin[cartes.length];
		
		Astar astar = new Astar();
		for (int i = 0; i < cartes.length; i ++) {
			System.out.println(cartes[i].getNom());
			chemins[i] = astar.rechercher(cartes[i]);
			System.out.println("Nombre Iterations: " + chemins[i].getNbIterations());
			System.out.println("Temps d'execution: " + chemins[i].getTempsExec());
			affichChemin(savCartes[i],chemins[i]);
		}
	}
	
	public static void affichChemin(Carte c, Chemin p) {
		int[] sommet;
		for (Object emp : p.getContenu()) {
			sommet = (int[]) emp;
			c.marquer(sommet);
		}
		c.afficher();
	}
	
}
