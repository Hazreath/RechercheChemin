package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionObjectToCoordsTImpossible;
import exceptions.ExceptionTabMalForme;
import interfaces.IExplorable;

/**
 * Objet carte contenant le labyrinthe � �tudier dans une matrice de caract�res
 * 
 * @author b.camp
 * @version 0.1
 */
public class Carte implements IExplorable {

	/** Dimension de la carte utilis�e par le projet (2D,3D...) */
	public final static int DIMENSION = 2;
	/** S�parateur utilis� par le fichier texte entre les coordonn�es */
	public final static String SEPARATEUR = "\t";
	/** Liste des caract�res repr�sentant des murs */
	public final static char[] MURS = { '-', '+', '|' };
	private static final char CHAR_MARQUAGE = 'X';

	/** nom de la carte */
	private String nom;

//	private FileInputStream in;
	/** Flux Scanner (lecture) li� au fichier */
	private Scanner scanner;

	/** contenu de la carte (labyrinthe) */
	private char[][] contenu;
	/** coordonn�es de d�part de la machine dans le labyrinthe */
	private int[] depart;
	/** coordonn�es d'arriv�e de la machine dans le labyrinthe */
	private int[] arrivee;
	/** Valeurs maximales d'�l�ments */
	private int[] valMax;

	/**
	 * Constructeur par File, pointant sur la contenu de la carte � instancier
	 * @param src File pointant sur le fichier � analyser
	 * @throws ExceptionFichierMalforme si le fichier est malform�
	 * @throws ExceptionFichierInexistant si le fichier n'existe pas
	 */
	public Carte(File src) throws ExceptionFichierMalforme, ExceptionFichierInexistant {
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
	public void charger() throws ExceptionFichierMalforme {

		this.depart = new int[Carte.DIMENSION];
		this.arrivee = new int[Carte.DIMENSION];
		this.valMax = new int[Carte.DIMENSION];

		String[] temp;

		// Lecture du bandeau d'infos (4 premi�res lignes)
		try {
			this.nom = scanner.nextLine();
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			this.depart = strCoordToInt(temp);
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			this.arrivee = strCoordToInt(temp);
			temp = scanner.nextLine().split(Carte.SEPARATEUR);
			this.valMax = strCoordToInt(temp);
		} catch (ExceptionTabMalForme e) {
			throw new ExceptionFichierMalforme();
		}

		// Affectation du labyrinthe dans la matrice arrayCarte
		char[][] arrayCarte = new char[this.valMax[1]][this.valMax[0]]; // x,y
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
	
	public int[] getDepart() {
		return this.depart;
	}
	
	public int[] getArrivee() {
		return this.arrivee;
	}
	
	public void marquerChemin(int[] c) {
		this.contenu[c[0]][c[1]] = 'O';
	}

	@Override
	public boolean estArrivee(Object aAnalyser) throws ExceptionObjectToCoordsTImpossible {
		
		
		int[] a = objectToCoords(aAnalyser);
		int i;
		// si toutes les coordonn�es sont �gales alors i == DIMENSION
		for (i = 0; i < Carte.DIMENSION && a[i] == this.arrivee[i]; i++ ); // empty body
		

		return i == Carte.DIMENSION;
	}

	
	
	/**
	 * Convertit une instance Object d'origine int[] (de dimension �gale � celle du projet) en int[];
	 * @param arg Object � convertir
	 * @return int[] de dimension valide
	 * @throws ExceptionObjectToCoordsTImpossible si la case argument
	 *         n'est pas de dimension �gale � celle du projet
	 */
	public int[] objectToCoords(Object arg) throws ExceptionObjectToCoordsTImpossible {
		if (!(arg instanceof int[])) {
			throw new ExceptionObjectToCoordsTImpossible();
		}
		
		int[] a = (int[]) arg;
		
		if (a.length != Carte.DIMENSION) {
			throw new ExceptionObjectToCoordsTImpossible();
		}
		
		return a;
		
	}

	@Override
	/**
	 * Retourne la List des coordonn�es des suivants de la case aAnalyser
	 * @param aAnalyser Object, instance de int[], repr�sentant les coordonn�es de 
	 *                  la case dont on veut les suivants
	 * @return List<int[]> contenant la liste des suivants de la case aAnalyser
	 * @throws ExceptionObjectToCoordsTImpossible si la case argument
	 *         n'est pas de dimension �gale � celle du projet
	 */
	public LinkedList<int[]> getSuivant(Object aAnalyser) throws ExceptionObjectToCoordsTImpossible {
		LinkedList<int[]> listeSuiv = new LinkedList<int[]>();
		int[] indice = objectToCoords(aAnalyser); // coordonn�es case init
		int[] temp;
		System.out.print("indice : " ); aff(indice);
		for (int i = 0; i < Carte.DIMENSION; i++) {
				if (indice[i] > 0) { // admet un suivant � la case d'avant
					temp = indice.clone();
					temp[i] = temp[i] - 1;
					listeSuiv.add(temp);
						
				}
				
				if (indice[i] < valMax[i]) { // admet un suivant � la case d'apr�s
					temp = indice.clone();
					temp[i] = temp[i] + 1;
					listeSuiv.add(temp);
					
					
				}
			
			
		}
		return listeSuiv;
	}
	
	public static void aff(int[] t) {
		for (int i : t) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	@Override
	public void marquer(Object sommet) {
		try {
			int[] coords = objectToCoords(sommet);
			this.contenu[coords[0]][coords[1]] = CHAR_MARQUAGE;
		} catch (ExceptionObjectToCoordsTImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean estMarque(Object voisin) {
		// TODO Auto-generated method stub
		int[] coords = new int[0];
		try {
			coords = objectToCoords(voisin);
		} catch (ExceptionObjectToCoordsTImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.contenu[coords[0]][coords[1]] == CHAR_MARQUAGE;
	}


}
