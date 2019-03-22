package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ExceptionTabMalForme;
import source.Carte;

/**
 * Classe permettant de charger une carte à partir d'un fichier texte au format valide
 * Le fichier doit commencer par un titre, suivi des coordonnées 2D des points suivants :
 * - Point de départ
 * - Point d'arrivée
 * - Fin de fichier
 * Soit un bandeau initial de 4 lignes.
 * Le labyrinthe est constitué de murs compris dans Carte.MURS;
 *
 * @author b.camp
 * @version 0.1
 */
public class ChargeMap {

	/* TODO
	 * virer url
	 * charger la map à la construction de l'objet
	 */

	/** url du fichier à charger */
	private String url;
	/** Objet File lié au fichier */
	private File source;
	private FileInputStream in;
	/** Flux Scanner (lecture) lié au fichier */
	private static Scanner scanner;

	/**
	 * Constructeur par défaut
	 * @param url url du fichier
	 */
	public ChargeMap(String url) {
		this.url = url;
		this.source = new File(url);

	}

	/**
	 * Affiche le fichier texte pointé par this.source
	 * @debugMethod
	 */
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
	 * Charge la carte pointée par le fichier this.source
	 * @return Objet Map contenant le labyrinthe présent dans le fichier
	 * @throws ExceptionTabMalForme
	 * @throws NumberFormatException
	 */
	public Carte charger() throws NumberFormatException, ExceptionTabMalForme {

		ArrayList<Character> contenu = new ArrayList<Character>();
		//in = new FileInputStream(source);
		try {
			scanner = new Scanner(this.source);
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

		// Affectation du labyrinthe dans la matrice arrayCarte
		char[][] arrayCarte = new char[finFichier[1]][finFichier[0]];
		String line;
		for (int i = 0; i < arrayCarte.length && scanner.hasNextLine(); i++) {
			line = scanner.nextLine();
			arrayCarte[i] = line.toCharArray();
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
	 * @throws ExceptionTabMalForme si coords est de dimension invalide (différente de Carte.DIMENSION)
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

		Carte map = lel.charger();


	}

	public static void affichTab(int[] t) {
		System.out.print("[");
		for (int i : t) {
			System.out.print(i + ", ");
		}
		System.out.print("]");
	}


}
