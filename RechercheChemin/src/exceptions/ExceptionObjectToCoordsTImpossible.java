package exceptions;

public class ExceptionObjectToCoordsTImpossible extends Exception {
	
	private static final long serialVersionUID = -7504561696528943936L;
	public static String msg = "Erreur : Impossible de convertir cet objet en int[] compatible : dimension ou type d'origine invalide.";
	
	public ExceptionObjectToCoordsTImpossible() {
		super(msg);
	}
}
