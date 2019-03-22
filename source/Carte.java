package source;

import java.util.Arrays;

/**
 * Objet carte contenant le labyrinthe � �tudier dans une matrice de caract�res
 * @author b.camp
 * @version 0.1
 */
public class Carte {

	/** Dimension de la carte utilis�e par le projet (2D,3D...) */
	public final static int DIMENSION = 2;
	/** S�parateur utilis� par le fichier texte entre les coordonn�es */
	public final static String SEPARATEUR = "\t";
	/** Liste des caract�res repr�sentant des murs */
	public final static char[] MURS = {'-','+','|'};

	/** nom de la carte */
	private String nom;
	/** contenu de la carte (labyrinthe) */
	private char[][] contenu;
	/** coordonn�es de d�part de la machine dans le labyrinthe */
	private int[] depart;
	/** coordonn�es d'arriv�e de la machine dans le labyrinthe */
	private int[] arrivee;

	/**
	 * Constructeur par d�faut
	 * @param nom nom de la carte
	 * @param contenu contenu de la carte
	 * @param depart coordonn�es de d�part
	 * @param arrivee coordonn�es d'arriv�e
	 */
	public Carte(String nom, char[][] contenu, int[] depart, int[] arrivee) {
		this.nom = nom;
		this.contenu = contenu;
		this.depart = depart;
		this.arrivee = arrivee;
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

	public char getContent(int x, int y) {
		// if entre bornes TODO
		return this.contenu[x][y];
	}
}
