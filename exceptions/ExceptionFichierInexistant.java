package exceptions;

public class ExceptionFichierInexistant extends Exception {

	public static String msg = "Erreur : Le fichier sp�cifi� est introuvable.";
	
	public ExceptionFichierInexistant() {
		super(msg);
	}

}
