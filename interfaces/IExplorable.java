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
	public T getSuivant(T aAnalyser) throws ExceptionObjectToCoordsTImpossible;
	
	/**
	 * Retourne la case d�part relative � la carte
	 * @return coordonn�es de la case d�part
	 */
	public T getDepart();
	
	public boolean isDepart(T sommet);

	public void marquer(T sommet);

	public boolean estMarque(T voisin);

	public T getArrivee();
	
	


}
