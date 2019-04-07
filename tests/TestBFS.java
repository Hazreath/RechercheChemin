package tests;

import java.io.File;
import java.net.URL;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionObjectToCoordsTImpossible;
import models.BFS;
import models.Carte;
import models.Chemin;
import models.ExceptionArriveeInatteignable;

public class TestBFS {

	
	public static void main (String[] args) throws ExceptionFichierMalforme, ExceptionFichierInexistant, ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable {
		//URL lel = ClassLoader.class.getClassLoader().getResource("map.txt");
		final String URL_CARTES = ".\\cartes\\";
		
		String racine = "RechercheChemin-Benji/cartes/";
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
		BFS bfs = new BFS();
		for (int i = 0; i < cartes.length; i ++) {
			System.out.println(cartes[i].getNom());
			chemins[i] = bfs.rechercher(cartes[i]);
			affichChemin(savCartes[i],chemins[i]);
		}
	}
	
	public static URL getPath(String fichier) {
		return null;
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
