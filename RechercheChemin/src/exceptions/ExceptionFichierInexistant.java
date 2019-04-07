package exceptions;

public class ExceptionFichierInexistant extends Exception {

	
	private static final long serialVersionUID = -7400659188758137556L;
	
	public static String msg = "Erreur : Le fichier spécifié est introuvable.";
	
	public ExceptionFichierInexistant() {
		super(msg);
	}

}
