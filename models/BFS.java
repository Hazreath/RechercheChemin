package models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.ExceptionObjectToCoordsTImpossible;
import interfaces.IExplorable;

public class BFS<T> {


	public Chemin rechercher(IExplorable<T> carte) throws ExceptionObjectToCoordsTImpossible {
		LinkedList<T> aTraiter = new LinkedList<T>();
		
		// Sauvegarde des parcours de l'algo et de l'origine de chaque sommet (etapes)
		LinkedList<Etape<T>> listeEtapes = new LinkedList<Etape<T>>();
		
		// Initialisation algo
		T sommet = carte.getDepart();
		listeEtapes.add(new Etape<T>(sommet));
		aTraiter.add(sommet);
		carte.marquer(sommet);
		
		// Traitement
		while (!aTraiter.isEmpty()) {
			sommet = aTraiter.getLast(); // ?
			aTraiter.remove(sommet);
			
			suivants = (LinkedList<T>) carte.getSuivant(sommet);
			
			// Voisins
			for (T voisin : suivants) {
				if (!(carte.estMarque(voisin))) {
					// Sauvegarde du sommet courant et son prédécesseur
					
					
					
					parcours.add(etape);
					aTraiter.add(voisin);
					carte.marquer(voisin);
				}
			}
		}
		
		return new Chemin();
		
	}
	
	
	
	
} 
