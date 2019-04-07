package interfaces;

import java.util.List;

import exceptions.ExceptionObjectToCoordsTImpossible;

/**
 * Interface générique
 * @author b.camp
 *
 * @param <T> Structure de données utilisée
 */
public interface IExplorable<T> {
	
	/**
	 * Indique si la case argument est l'arrivee de la carte
	 * @param aAnalyser case à analyser
	 * @return true si la case est l'arrivée, false sinon
	 * @throws ExceptionObjectToCoordsTImpossible 
	 */
	public boolean estArrivee(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la liste des cases adjacentes à la case argument
	 * @param aAnalyser case à analyser
	 * @return
	 * @throws ExceptionObjectToCoordsTImpossible 
	 */
	public T getSuivant(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la case départ relative à la carte
	 * @return coordonnées de la case départ
	 */
	public T getDepart();
	
	public boolean isDepart(T sommet);

	public void marquer(T sommet);

	public boolean estMarque(T voisin);

	public T getArrivee();
	
	


}
