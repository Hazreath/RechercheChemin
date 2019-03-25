package exceptions;

public class ExceptionFichierInexistant extends Exception {

	public static String msg = "Erreur : Le fichier spécifié est introuvable.";
	
	public ExceptionFichierInexistant() {
		super(msg);
	}

}
