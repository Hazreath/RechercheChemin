package source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionTabMalForme;

/**
 * Objet carte contenant le labyrinthe � �tudier dans une matrice de caract�res
 * 
 * @author b.camp
 * @version 0.1
 */
public class Carte {

	/** Dimension de la carte utilis�e par le projet (2D,3D...) */
	public final static int DIMENSION = 2;
	/** S�parateur utilis� par le fichier texte entre les coordonn�es */
	public final static String SEPARATEUR = "\t";
	/** Liste des caract�res repr�sentant des murs */
	public final static char[] MURS = { '-', '+', '|' };

	/** nom de la carte */
	private String nom;

	private FileInputStream in;
	/** Flux Scanner (lecture) li� au fichier */
	private Scanner scanner;

	/** contenu de la carte (labyrinthe) */
	private char[][] contenu;
	/** coordonn�es de d�part de la machine dans le labyrinthe */
	private int[] depart;
	/** coordonn�es d'arriv�e de la machine dans le labyrinthe */
	private int[] arrivee;

	/**
	 * 
	 * @param src
	 * @throws ExceptionFichierMalforme
	 * @throws ExceptionTabMalForme
	 * @throws ExceptionFichierInexistant
	 */
	public Carte(File src) throws ExceptionFichierMalforme, ExceptionTabMalForme, ExceptionFichierInexistant {
		try {
			this.scanner = new Scanner(src);

		} catch (FileNotFoundException e) {

			throw new ExceptionFichierInexistant();
		}
		this.charger();
	}

	/**
	 * Charge la carte point�e par le fichier this.source
	 * 
	 * @return Objet Map contenant le labyrinthe pr�sent dans le fichier
	 * @throws ExceptionFichierMalforme Si la fonction ne parvient pas � charger les
	 *                                  donn�es contenues dans le bandeau du fichier
	 * @throws ExceptionTabMalForme     @see StrCoordToInt
	 */
	public void charger() throws ExceptionFichierMalforme, ExceptionTabMalForme {

		ArrayList<Character> contenu = new ArrayList<Character>();
		// in = new FileInputStream(source);

		// R�cup�ration des informations de la carte
		String nom;

		this.depart = new int[Carte.DIMENSION];
		this.arrivee = new int[Carte.DIMENSION];
		int[] finFichier = new int[Carte.DIMENSION];

		String[] temp;

		// Lecture du bandeau d'infos (4 premi�res lignes)
		try {
			this.nom = scanner.nextLine();
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			this.depart = strCoordToInt(temp);
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			this.arrivee = strCoordToInt(temp);
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			finFichier = strCoordToInt(temp);
		} catch (ExceptionTabMalForme e) {
			throw new ExceptionFichierMalforme();
		}

		// Affectation du labyrinthe dans la matrice arrayCarte
		char[][] arrayCarte = new char[finFichier[1]][finFichier[0]];
		String line;
		for (int i = 0; i < arrayCarte.length && scanner.hasNextLine(); i++) {
			line = scanner.nextLine();
			arrayCarte[i] = line.toCharArray();
		}

		this.contenu = arrayCarte;
	}

	/**
	 * Transforme un tableau de coordonn�es String en tableau de coordonn�es int
	 * 
	 * @param coords tableau de coordonn�es String � transformer
	 * @return tableau de coordonn�es int
	 * @throws java.lang.NumberFormatException si un des indices du tableau coords
	 *                                         est invalide ou null
	 * @throws ExceptionTabMalForme            si coords est de dimension invalide
	 *                                         (diff�rente de Carte.DIMENSION)
	 * @throws ExceptionFichierMalforme        Si la ligne � parser ne contient pas
	 *                                         d'entiers
	 */
	public static int[] strCoordToInt(String[] coords)
			throws java.lang.NumberFormatException, ExceptionTabMalForme, ExceptionFichierMalforme {

		if (coords.length != Carte.DIMENSION)
			throw new ExceptionTabMalForme();

		int[] newCoord = new int[coords.length];
		for (int i = 0; i < coords.length; i++) {
			try {
				newCoord[i] = Integer.parseInt(coords[i]);
			} catch (NumberFormatException e) {
				throw new ExceptionFichierMalforme();
			}

		}
		return newCoord;
	}

	@Override
	public String toString() {
		String str = "";

		for (char[] line : contenu) {
			for (char c : line) {
				str += c;
			}
			str += '\n';
		}

		return str;
	}
	
	public void afficher() {
		System.out.println(this.toString());
	}

	/**
	 * Retourne le caract�re contenu � la aux coordonn�es [x;y] case de la carte
	 * this
	 * 
	 * @param x coordonn�es x
	 * @param y coordonn�es y
	 * @return null si hors des bornes charact�re contenu dans la case [x;y] sinon
	 */
	public char getContenu(int x, int y) {
		return ((x > 0 && x < this.contenu.length) && (y > 0 && y < this.contenu[0].length)) ? this.contenu[x][y]
				: null;
	}
}
