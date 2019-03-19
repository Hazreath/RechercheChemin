package exceptions;

/**
 * Exception lanc�e lorsqu'un tableau n'est pas de dimension �gale � celle du projet (Carte.DIMENSION)
 * @author b.camp
 *
 */
public class ExceptionTabMalForme extends Exception{

	public static String msg = "Erreur: le tableau renseign� n'est pas de dimension valide.";
	public ExceptionTabMalForme() {
		super(msg);
	}
}
