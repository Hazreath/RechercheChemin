package exceptions;

/**
 * Exception lancée lorsqu'un tableau n'est pas de dimension égale à celle du projet (Carte.DIMENSION)
 * @author b.camp
 *
 */
public class ExceptionTabMalForme extends Exception{

	public static String msg = "Erreur: le tableau renseigné n'est pas de dimension valide.";
	public ExceptionTabMalForme() {
		super(msg);
	}
}
