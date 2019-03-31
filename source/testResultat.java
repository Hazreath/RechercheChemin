import java.util.ArrayList;
import java.util.List;

public class testResultat {

	public static void main(String[] args) {
		Resultat r = new Resultat();
		r.afficher(); // pas censé fonctionner
		
		int[] lel = {0,38};
		int[] lel1 = {39,38};
		
		
		List<int[]> liste = new ArrayList();
		liste.add(0, lel);
		liste.add(1, lel1);

		
		Chemin c = new Chemin();
		c.setContenu(liste);
		r.setC(c);
		r.afficher();
	}

}
