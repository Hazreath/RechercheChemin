package exceptions;

public class ExceptionArriveeInatteignable extends Exception {

	public static String msg = "Erreur : La carte utilisée ne possède pas de chemin vers l'arrivée.";
	public ExceptionArriveeInatteignable() {
		super(msg);
	}
}
