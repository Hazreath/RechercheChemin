package tests;

import java.io.File;
import java.util.Scanner;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionTabMalForme;
import source.Carte;

public class TestCarte {

	public static void main(String[] args ) throws ExceptionFichierMalforme, ExceptionTabMalForme, ExceptionFichierInexistant {
		File valide = new File("G:\\Workspace\\sanic.zip_expanded\\sanic\\RechercheChemin-master.zip_expanded\\RechercheChemin-master\\tests\\map.txt");
		File invalide = new File("");
		File malforme = new File("G:\\Workspace\\sanic.zip_expanded\\sanic\\RechercheChemin-master.zip_expanded\\RechercheChemin-master\\tests\\inv.txt");
		
		Carte ok = new Carte(valide);
		try {
			Carte malf = new Carte(malforme);
		} catch (ExceptionFichierMalforme e) {
			System.out.println("Fichier malformé : OK");
		}
	
		//ok.afficher();
		
		// test getContenu
		char c;
//		try {
//			c = ok.getContenu(-1, 2);
//			System.out.println(c);
//		} catch (NullPointerException n ) {
//			System.out.println("nullpointer ok");
//		}
		
		c = ok.getContenu(2, 5);
		System.out.println(c);
		
		Scanner in = new Scanner(System.in);
		in.nextLine();
	
	}
}
