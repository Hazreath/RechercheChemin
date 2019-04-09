package application;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import exceptions.ExceptionNonScorable;
import exceptions.ExceptionObjectToCoordsTImpossible;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Astar;
import models.BFS;
import models.Carte;
import models.Chemin;
import models.ExceptionArriveeInatteignable;

/**
 * Classe controller de l'interface
 * 
 * @author r.gorisse
 * @version 0.3
 */

public class Controller {

	//Dossier contenant les cartes
	final static String repoCartes = System.getProperty("user.dir") + "/src/cartes/";
	
	@FXML
	private ComboBox<String> cartes;

	@FXML
	private ComboBox<String> algorithmes;

	@FXML
	private Button executer;

	@FXML
	private Label nomCarte;
	
	@FXML
	private Label tpsExec;

	@FXML
	private Label nbIte;

	@FXML
	private TextArea carte;

	// liste observable pour remplir la comboBox cartes.
	ObservableList<String> list = FXCollections.observableArrayList();

	/**
	 * description : Méthode qui permet de pracourir un fichier (ici pour les
	 * différentes cartes) ATTENTION : Bien changer la police de la textArea pour
	 * une carte en .txt
	 * 
	 * @param folder
	 */
	public void listerFichier(File folder) {
		for (File currentFile : folder.listFiles()) {
			if (currentFile.isDirectory()) {
				listerFichier(currentFile); // Si c'est encore un dossier, on le reparcours
			} else {
				list.add(currentFile.getName());
				System.out.println(currentFile.getName());

			}
		}

	}

	/**
	 * Description : Méthode qui retourne une liste d'observable de string afin de remplir la comboBox algorithmes. 
	 * @return ObservableList<String> liste
	 */
	public ObservableList<String> listerAlgos() {
		ObservableList<String> liste = FXCollections.observableArrayList();
		liste.add("Sans algorithme");
		liste.add("BFS");
		liste.add("AStar");
		return liste;

	}

	/**
	 * description : est appellé à la construction de la page.
	 * 
	 * @throws IOException
	 */
	@FXML
	public void initialize() throws IOException {
		
		File folder = new File(repoCartes);
		// lister pour ensuite remplir les combo-box
		listerFichier(folder);
		cartes.setItems(list);

		algorithmes.setItems(listerAlgos());

		// selectionner par défaut
		if (cartes.getSelectionModel() != null) {
			cartes.getSelectionModel().select(0);

		} else {
			System.out.println("Pas de cartes ..."); // normalement déjà géré par ExceptionFichierInexistant
		}

		// Sur un test différent
		if (algorithmes.getSelectionModel() != null) {
			algorithmes.getSelectionModel().select(0);

		} else {
			System.out.println("Pas d'algos..."); // normalement déjà géré par ExceptionFichierInexistant
		}
	}

	/**
	 * description : associé au bouton "executer", cette méthode permet d'afficher
	 * sur la textArea "carte" la carte et le chemin associés au couple carte/algo
	 * 
	 * @throws ExceptionFichierMalforme
	 * @throws ExceptionFichierInexistant
	 * @throws ExceptionArriveeInatteignable
	 * @throws ExceptionObjectToCoordsTImpossible
	 * @throws ExceptionNonScorable 
	 */
	@FXML
	public void onExecuter() throws ExceptionFichierMalforme, ExceptionFichierInexistant,
			ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable, ExceptionNonScorable {
		/* && algorithmes.getValue() == null */
		if (cartes.getValue() == null) {
			carte.setText("Veuillez choisir une carte ET un algorithme.");
		} else {
			String sCarte = cartes.getSelectionModel().getSelectedItem();
			System.out.println("Sélection de la carte : " + sCarte);

			Carte c = new Carte(new File(repoCartes + sCarte));
			Carte c2 = new Carte(new File(repoCartes + sCarte));

			// Ajouter algo
			String sAlgo = algorithmes.getSelectionModel().getSelectedItem();
			Chemin solution = null;

			switch (sAlgo) {
			case "BFS":
				BFS algo = new BFS();
				try {
					solution = algo.rechercher(c);
				} catch (ExceptionArriveeInatteignable e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("La carte choisie ne possède pas de chemin vers l'arrivée.");
					a.showAndWait();
				}
				
				int[] s;
				for (Object sommet : solution.getContenu()) {
					s = (int[]) sommet;
					c2.marquer(sommet);

				}
				
				// Ecrire depart & arrivee
				c2.ecrire(solution.getContenu().get(0), 'D');
				c2.ecrire(solution.getContenu().get(solution.getContenu().size() - 1), 'A');
				// Afficher
				carte.setText(c2.toString());
				
				// remplir les statistiques
				tpsExec.setText(solution.getTempsExec());
				nbIte.setText(Integer.toString(solution.getNbIterations()));
				break;

			case "AStar":
				Astar algoA = new Astar();
				try {
					solution = algoA.rechercher(c);
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("La carte choisie ne possède pas de chemin vers l'arrivée.");
					a.showAndWait();
				}
				int[] so;
				for (Object sommet : solution.getContenu()) {
					so = (int[]) sommet;
					c2.marquer(sommet);

				}
				// Ecrire depart & arrivee
				c2.ecrire(solution.getContenu().get(0), 'D');
				c2.ecrire(solution.getContenu().get(solution.getContenu().size() - 1), 'A');
				// Afficher
				carte.setText(c2.toString());
				// remplir les statistiques
				tpsExec.setText(solution.getTempsExec());
				nbIte.setText(Integer.toString(solution.getNbIterations()));
				break;
				
			default:
				System.out.println("default");
				System.out.println("afficher juste la carte");
				// Afficher
				carte.setText(c2.toString());
				
				// remplir les statistiques
				tpsExec.setText("");
				nbIte.setText("");
				break;
				
			}

			this.nomCarte.setText(c.getNom());
			System.out.println("Sélection de l'algorithme : " + sAlgo);

			
		}

	}

}
