package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ExceptionTabMalForme;
import source.Carte;

/**
 *
 * @author b.camp
 *
 */
public class ChargeMap {

	private String url;
	private File source;
	private FileInputStream in;
	private static Scanner scanner;

	public ChargeMap(String url) {
		this.url = url;
		this.source = new File(url);

	}

	public void afficher() {

		try {
			this.scanner = new Scanner(this.source);
		} catch (FileNotFoundException e) {
			// TODO exc
			System.out.println("Erreur : le fichier n'existe pas !");
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}

	/**
	 * Charge
	 * @return
	 * @throws ExceptionTabMalForme
	 * @throws NumberFormatException
	 */
	public static Carte charger(File source) throws NumberFormatException, ExceptionTabMalForme {

		ArrayList<Character> contenu = new ArrayList<Character>();
		//in = new FileInputStream(source);
		try {
			scanner = new Scanner(source);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Récupération des informations de la carte
		String nom;
		int[] depart, arrivee, finFichier;
		depart = new int[Carte.DIMENSION];
		arrivee = new int[Carte.DIMENSION];
		finFichier = new int[Carte.DIMENSION];

		String[] temp;

		// Lecture du bandeau d'infos (4 premières lignes)
		nom = scanner.nextLine();
		temp = scanner.nextLine().split(Carte.SEPARATEUR);
		depart = strCoordToInt(temp);
		temp = scanner.nextLine().split(Carte.SEPARATEUR);
		arrivee = strCoordToInt(temp);
		temp = scanner.nextLine().split(Carte.SEPARATEUR);
		finFichier = strCoordToInt(temp);

		/* TODO
			- Calculer nbLignes, taille de la ligne via finFichier
			- charger dans le char[][]
		*/
		char[][] arrayCarte = char[finFichier[1]][finFichier[0]];
		while (scanner.hasNextLine()) {

		}

		Carte map = new Carte(nom,arrayCarte,depart,arrivee);
		return map;
	}

	/**
	 * Transforme un tableau de coordonnées String en tableau
	 * de coordonnées int
	 * @param coords tableau de coordonnées String à transformer
	 * @return tableau de coordonnées int
	 * @throws java.lang.NumberFormatException si un des indices du tableau coords est invalide ou null
	 * @throws ExceptionTabMalForme si coords est de dimension invalide
	 */
	public static int[] strCoordToInt(String[] coords)
			throws java.lang.NumberFormatException, ExceptionTabMalForme {

		if (coords.length != Carte.DIMENSION)
			throw new ExceptionTabMalForme();
		int[] newCoord = new int[coords.length];
		for (int i = 0; i < coords.length; i++) {
			newCoord[i] = Integer.parseInt(coords[i]);
		}
		return newCoord;
	}

	/* @ TESTS METHODS */
	public static void main(String[] args) throws NumberFormatException, ExceptionTabMalForme {
		String src = "U:\\eclipse-workspace\\SANIC\\src\\io\\map.txt";
		ChargeMap lel = new ChargeMap(src);

		//lel.afficher();
		String[] c = {"1"};
		int[] conv = strCoordToInt(c);
		affichTab(conv);

	}

	public static void affichTab(int[] t) {
		System.out.print("[");
		for (int i : t) {
			System.out.print(i + ", ");
		}
		System.out.print("]");
	}


}
