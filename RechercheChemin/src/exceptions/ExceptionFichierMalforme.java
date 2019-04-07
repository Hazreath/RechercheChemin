package exceptions;

/**
 * Exception lancée lors d'une erreur de formatage du fichier contenant le labyrinthe
 * 
 * @author b.camp
 *
 */
public class ExceptionFichierMalforme extends Exception {
	
	private static final long serialVersionUID = -7509094696528943936L;
	public static String msg = "Erreur : Le fichier spécifié est malformé.";
	
	public ExceptionFichierMalforme() {
		super(msg);
	}
}
