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
	public List<T> getSuivant(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la case départ relative à la carte
	 * @return coordonnées de la case départ
	 */
	public T getDepart();
	
	/**
	 * Indique si la case argument est le départ de la carte
	 * @param sommet case à analyser
	 * @return true si sommet est la case départ
	 */
	public boolean isDepart(T sommet);

	/**
	 * Marque d'un caractère la case sommet de la carte argument
	 * @param sommet case à marquer
	 */
	public void marquer(T sommet);

	/**
	 * Indique si la case argument est marquée
	 * @param voisin
	 * @return
	 */
	public boolean estMarque(T voisin);
	
	


}
