package exceptions;

/**
 * Exception lancée lors d'une erreur de formatage du fichier contenant le labyrinthe
 * 
 * @author b.camp
 *
 */
public class ExceptionFichierMalforme extends Exception {
	public static String msg = "Erreur : Le fichier spécifié est malformé.";
	
	public ExceptionFichierMalforme() {
		super(msg);
	}
}
