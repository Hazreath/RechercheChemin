package exceptions;

/**
 * Exception lanc�e lorsqu'un tableau n'est pas de dimension �gale � celle du projet (Carte.DIMENSION)
 * @author b.camp
 *
 */
public class ExceptionTabMalForme extends Exception{

	
	private static final long serialVersionUID = 2478521686789965741L;
	public static String msg = "Erreur: le tableau renseign� n'est pas de dimension valide.";
	public ExceptionTabMalForme() {
		super(msg);
	}
}
