package interfaces;

/**
 * Interface IScorable
 * d�finit le prototype de la m�thode de la fonction d'heuristique
 * @author Luc
 *
 * @param <T>
 */
public interface IScorable<T> {

	/**
	 * Fonction d'heuristique
	 * calcul le score de la position courante en fonctiuon de :
	 *  - la distance par rapport au d�part (en nombre de cases)
	 *  - et la distance par rapport � l'arriv�e
	 * @param d nombre de cases depuis le d�part
	 * @param courant position courante
	 * @param arrivee position d'arriv�e
	 * @return le score associ� � la position courante 
	 */
	public double calculScore(int d, T courant, T arrivee);
	
}
