package models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.ExceptionArriveeInatteignable;
import exceptions.ExceptionObjectToCoordsTImpossible;
import interfaces.IExplorable;

public class BFS<T> {


	public Chemin rechercher(IExplorable<T> carte) throws ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable {
		LinkedList<T> aTraiter = new LinkedList<T>();
		
		// Sauvegarde des parcours de l'algo et de l'origine de chaque sommet (etapes)
		LinkedList<Etape<T>> listeEtapes = new LinkedList<Etape<T>>();
		LinkedList<T> suivants;
		
		// Initialisation algo
		T sommet = carte.getDepart();
		Etape<T> etapeCourante = new Etape<T>(sommet);
		Etape<T> etapePrec;
		listeEtapes.add(etapeCourante);
		aTraiter.add(sommet);
		carte.marquer(sommet);
		boolean arrivee = false;
		
		// Traitement
		while (!aTraiter.isEmpty() && !arrivee) {
			
			etapePrec = etapeCourante;
			sommet = aTraiter.getFirst(); // ?
			aTraiter.remove(sommet);
			
			suivants = (LinkedList<T>) carte.getSuivant(sommet);
			
			// Voisins
			for (T voisin : suivants) {
				if (!(carte.estMarque(voisin))) {
					// Sauvegarde du sommet courant et son prédécesseur
					listeEtapes.add(new Etape<T>(etapePrec,voisin));
					
					aTraiter.add(voisin);
					carte.marquer(voisin);
					arrivee = carte.estArrivee(voisin);
				}
			}
		}
		
		
		// Fin algo
		
		// Gestion exc : la carte est non finissable
		if (!(carte.estArrivee(listeEtapes.getLast().getCourant()))) {
			throw new ExceptionArriveeInatteignable();
		}
		LinkedList<T> chemin = new LinkedList<T>();
		Etape<T> e = listeEtapes.getLast();
		
		
		// Parcours inverse à partir de l'arrivée jusqu'au depart
		while (!carte.isDepart(e.getCourant())) {
			chemin.add(e.getCourant());
			e = e.getPrecedent();
		}
		chemin.add(carte.getDepart());
		
		LinkedList<T> cheminReversed = new LinkedList<T>();
		Iterator reverse = chemin.descendingIterator();
		
		while (reverse.hasNext()) {
			cheminReversed.add((T) reverse.next());
		}
		return new Chemin((List<T>) cheminReversed);
		
	}
	
	
	
	
} 
