package application;

import java.io.File;
import java.io.IOException;

import exceptions.ExceptionFichierInexistant;
import exceptions.ExceptionFichierMalforme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Carte;

/**
 * Classe controller de l'interface
 * 
 * @author r.gorisse
 * @version 0.1
 */

public class Controller {

	@FXML
	private ComboBox<String> cartes;

	@FXML
	private ComboBox<?> algorithmes;

	@FXML
	private Button executer;

	@FXML
	private Label tpsExec;

	@FXML
	private Label nbIte;

	@FXML
	private TextArea carte;

	// liste observable pour remplir la comboBox cartes.
	ObservableList<String> list = FXCollections.observableArrayList();


	/**
	 * description : Méthode qui permet de pracourir un fichier (pour les différentes cartes)
	 * ATTENTION : Bien changer la police de la textArea pour une carte en .txt
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
		cartes.setItems(list);

	}
	
	@FXML
	public void initialize() throws IOException {		
		final File folder = new File("D:\\Eclipse\\RechercheChemin\\cartes");
		listerFichier(folder);
	}

	/**
	 * description : associé au bouton "executer", cette méthode permet d'afficher sur la textArea "carte" 
	 * la carte et le chemin associés au couple carte/algo
	 * 
	 * @throws ExceptionFichierMalforme
	 * @throws ExceptionFichierInexistant
	 */
	@FXML
	public void onExecuter() throws ExceptionFichierMalforme, ExceptionFichierInexistant {
		/* && algorithmes.getValue() == null */
		if(cartes.getValue() == null) {
			carte.setText("Veuillez choisir une carte ET un algorithme.");
		} else {
			String s = cartes.getSelectionModel().getSelectedItem();
			System.out.println(s);

			Carte c = new Carte(new File("D:\\Eclipse\\RechercheChemin\\cartes\\" + s)); //TODO rendre générique

			carte.setText(c.toString());
			//TODO ajouter algo
			//TODO remplir les statistiques
			tpsExec.setText("tpsExec");
			nbIte.setText("nbIte");
		}


	}


}
