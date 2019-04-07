package models;
import java.util.List;

public class Chemin {
	private List<int[]> contenu;

	public List<int[]> getContenu() {
		return contenu;
	}

	public void setContenu(List<int[]> contenu) {
		this.contenu = contenu;
	}
	
	public boolean tester(Resultat r) {
		return true;
	}
}
