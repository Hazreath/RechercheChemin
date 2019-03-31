import java.util.ArrayList;
import java.util.List;

public class Resultat implements IResultat {

	private final static int TAILLE = 40;
	
	private Chemin c;
	
	private char[][] matriceChemin = new char[TAILLE][TAILLE];

	public Resultat() {

	}

	public void afficher()  {
		System.out.println("nbIteration : " + nbIteration + " timeExec : " + timeExec);
		
		// remplir matriceChemin d'espace
		for(int i = 0; i < TAILLE; i++ ) {
			for(int j = 0; j < TAILLE; j++) {
				if(j == TAILLE - 1) {
					matriceChemin[i][j] = '\n';

				} else {
					matriceChemin[i][j] = '-';
				}
			}
		}
		
		// remplacer aux bons endroits par des 'O'
		if( this.c != null) {
			if(this.c.getContenu() != null) {
				List<int[]> tabC = new ArrayList();
				tabC = this.c.getContenu();
				for(int i = 0; i < tabC.size(); i++) {
					int x = tabC.get(i)[0];
					int y = tabC.get(i)[1];
					this.matriceChemin[x][y] = 'O';
				}
			}

		}
		
		// afficher
		for(int i = 0; i < TAILLE; i++ ) {
			for(int j = 0; j < TAILLE; j++) {
				System.out.print(matriceChemin[i][j]);
			}
		}

	}

	public Chemin getC() {
		return c;
	}

	public void setC(Chemin c) {
		this.c = c;
	}

}
