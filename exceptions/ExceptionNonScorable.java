package exceptions;

public class ExceptionNonScorable extends Exception {

	public static String msg = "Erreur : La carte n'implémente pas l'interface IScorable.";
	public ExceptionNonScorable() {
		super(msg);
	}
}
