package source;

import java.util.Arrays;

/**
 * Objet carte contenant le labyrinthe à étudier dans une matrice de caractères
 * @author b.camp
 * @version 0.1
 */
public class Carte {

	/** Dimension de la carte utilisée par le projet (2D,3D...) */
	public final static int DIMENSION = 2;
	/** Séparateur utilisé par le fichier texte entre les coordonnées */
	public final static String SEPARATEUR = "\t";
	/** Liste des caractères représentant des murs */
	public final static char[] MURS = {'-','+','|'};

	/** nom de la carte */
	private String nom;
	/** contenu de la carte (labyrinthe) */
	private char[][] contenu;
	/** coordonnées de départ de la machine dans le labyrinthe */
	private int[] depart;
	/** coordonnées d'arrivée de la machine dans le labyrinthe */
	private int[] arrivee;

	/**
	 * Constructeur par défaut
	 * @param nom nom de la carte
	 * @param contenu contenu de la carte
	 * @param depart coordonnées de départ
	 * @param arrivee coordonnées d'arrivée
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
