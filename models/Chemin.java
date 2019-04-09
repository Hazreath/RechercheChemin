package models;
import java.util.List;

/**
 * Description : Objet retourné par les algorithmes
 * compose la liste des sommets, le temps d'éxécution, et le nombre d'itérations 
 * @author r.gorisse
 *
 * @param <T>
 */
public class Chemin<T>{
	private int nbIterations;
	private String tempsExec;
	private List<T> contenu;
	
	/**
	 * 
	 * @param nbIte
	 * @param time
	 * @param c
	 */
	public Chemin(int nbIte,long time,List<T> c) {
		this.nbIterations = nbIte;
		this.tempsExec = time + " ns";
		this.contenu = c;
	}

	/**
	 * 
	 * @return nbIterations
	 */
	public int getNbIterations() {
		return nbIterations;
		
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getTempsExec() {
		return this.tempsExec;
	}
	/**
	 * 
	 * @return List<T>
	 */
	public List<T> getContenu() {
		return contenu;
	}

	/**
	 * 
	 * @param contenu
	 */
	public void setContenu(List<T> contenu) {
		this.contenu = contenu;
	}
	
	/**
	 *description : méthode qui permet d'afficher le chemin
	 */
	public void afficher() {
		for (T sommet : contenu) {
			System.out.println(sommet.toString());
		}
	}
}
