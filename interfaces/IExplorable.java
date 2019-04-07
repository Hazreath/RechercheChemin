package interfaces;

import java.util.List;

import exceptions.ExceptionObjectToCoordsTImpossible;

/**
 * Interface g�n�rique
 * @author b.camp
 *
 * @param <T> Structure de donn�es utilis�e
 */
public interface IExplorable<T> {
	
	/**
	 * Indique si la case argument est l'arrivee de la carte
	 * @param aAnalyser case � analyser
	 * @return true si la case est l'arriv�e, false sinon
	 * @throws ExceptionObjectToCoordsTImpossible 
	 */
	public boolean estArrivee(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la liste des cases adjacentes � la case argument
	 * @param aAnalyser case � analyser
	 * @return
	 * @throws ExceptionObjectToCoordsTImpossible 
	 */
	public List<T> getSuivant(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la case d�part relative � la carte
	 * @return coordonn�es de la case d�part
	 */
	public T getDepart();
	
	/**
	 * Indique si la case argument est le d�part de la carte
	 * @param sommet case � analyser
	 * @return true si sommet est la case d�part
	 */
	public boolean isDepart(T sommet);

	/**
	 * Marque d'un caract�re la case sommet de la carte argument
	 * @param sommet case � marquer
	 */
	public void marquer(T sommet);

	/**
	 * Indique si la case argument est marqu�e
	 * @param voisin
	 * @return
	 */
	public boolean estMarque(T voisin);
	
	


}
