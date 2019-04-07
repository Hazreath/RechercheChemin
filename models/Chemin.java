package models;
import java.util.List;

public class Chemin<T>{
	private List<T> contenu;
	
	public Chemin(List<T> c) {
		this.contenu = c;
	}
	
	
	public List<T> getContenu() {
		return contenu;
	}

	public void setContenu(List<T> contenu) {
		this.contenu = contenu;
	}
	
	public boolean tester(Resultat r) {
		return true;
	}
	
	public void afficher() {
		System.out.println(contenu.size());
		for (T sommet : contenu) {
			System.out.println(sommet.toString());
		}
	}
}
