package exceptions;

public class ExceptionArriveeInatteignable extends Exception {

	public static String msg = "Erreur : La carte utilis�e ne poss�de pas de chemin vers l'arriv�e.";
	public ExceptionArriveeInatteignable() {
		super(msg);
	}
}
