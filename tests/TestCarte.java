package tests;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionObjectToCoordsTImpossible;
import exceptions.ExceptionTabMalForme;
import models.Carte;

public class TestCarte {
	static URL lel = ClassLoader.class.getClassLoader().getResource("map.txt");
	
	static File valide = new File(lel.getPath());
	static Scanner in = new Scanner(System.in);
	
	public static void testCreation() throws ExceptionFichierMalforme, ExceptionFichierInexistant {
		System.out.println(lel.getPath());
		Carte ok = new Carte(valide);
		File invalide = new File("");
		File malforme = new File("G:\\Workspace\\sanic.zip_expanded\\sanic\\RechercheChemin-master.zip_expanded\\RechercheChemin-master\\tests\\inv.txt");
		
		
		try {
			Carte malf = new Carte(malforme);
		} catch (ExceptionFichierMalforme e) {
			System.out.println("Fichier malformé : OK");
		}
	
		ok.afficher();
		
	}
	
	public static void testGetContenu() throws ExceptionFichierMalforme, ExceptionFichierInexistant {
		// test getContenu$
		Carte ok = new Carte(valide);
		char c;
		try {
			c = ok.getContenu(-1, 2);
			
			System.out.println(c);
		} catch (NullPointerException n ) {
			System.out.println("nullpointer ok");
		}
		c = ok.getContenu(2, 5);
		System.out.println(c);
		
		// test hors bornes
		char p;
		try {
			p = ok.getContenu(555,555);
		} catch (NullPointerException n) {
			System.out.println("hors bornes ok");
		}
		
		
		
		
		
	}
	
	public static void testGetSuivant() throws ExceptionFichierMalforme, ExceptionFichierInexistant, ExceptionObjectToCoordsTImpossible {
		Carte ok = new Carte(valide);
		int[] standard = {10,10};
		int[] coord3 = {0,10};
		int[] coord2 = {0,0};
 		List<int[] >suiv = ok.getSuivant(standard);
 		List<int[] >suiv3 = ok.getSuivant(coord3);
 		List<int[] >suiv2 = ok.getSuivant(coord2);
		afficher(suiv);
		afficher(suiv3);
		afficher(suiv2);
		
		
		
	}
	public static void main(String[] args ) throws ExceptionFichierMalforme, ExceptionTabMalForme, ExceptionFichierInexistant, ExceptionObjectToCoordsTImpossible {
		
//		testCreation();
//		testGetContenu();
		testGetSuivant();
		
		
		
		in.close();
	
	}
	
	public static void afficher(List<int[]> t) {
		System.out.println("\nCoordonnées des suivants: ");
		for (int[] line : t) {
			for (int e : line) {
				System.out.print(e + ",");
			}
			System.out.println("\n");
		}
	}
}
