package interfaces;

/**
 * Interface IScorable
 * définit le prototype de la méthode de la fonction d'heuristique
 * @author Luc
 *
 * @param <T>
 */
public interface IScorable<T> {

	/**
	 * Fonction d'heuristique
	 * calcul le score de la position courante en fonctiuon de :
	 *  - la distance par rapport au départ (en nombre de cases)
	 *  - et la distance par rapport à l'arrivée
	 * @param d nombre de cases depuis le départ
	 * @param courant position courante
	 * @param arrivee position d'arrivée
	 * @return le score associé à la position courante 
	 */
	public double calculScore(int d, T courant, T arrivee);
	
}
