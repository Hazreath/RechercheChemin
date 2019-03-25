package exceptions;

/**
 * Exception lanc�e lors d'une erreur de formatage du fichier contenant le labyrinthe
 * 
 * @author b.camp
 *
 */
public class ExceptionFichierMalforme extends Exception {
	public static String msg = "Erreur : Le fichier sp�cifi� est malform�.";
	
	public ExceptionFichierMalforme() {
		super(msg);
	}
}
