package models;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import exceptions.ExceptionObjectToCoordsTImpossible;
import interfaces.IExplorable;

public class BFS<T> {

	public Chemin<T> rechercher(IExplorable<T> c) throws ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable {
		IExplorable<T> carte = c;
		int nbIte = 0;
		// Chronométrage
		long timeStart,timeElapsed;
		
		timeStart= System.nanoTime();
		// Liste des sommets à traiter
		LinkedList<Etape<T>> aTraiter = new LinkedList<Etape<T>>();
		
		// Sauvegarde des parcours de l'algo et de l'origine de chaque sommet (etapes)
		LinkedList<Etape<T>> listeEtapes = new LinkedList<Etape<T>>();
		LinkedList<T> suivants;
		
		// Initialisation algo
		T sommet = carte.getDepart();
		Etape<T> etapeCourante = new Etape<T>(null,sommet);
		listeEtapes.add(etapeCourante);
		aTraiter.add(etapeCourante);
		carte.marquer(sommet);
		boolean arrivee = false;

		T lastVoisin = null; // Tampon du dernier voisin analysé
		
		// Traitement
		while (!aTraiter.isEmpty() && !arrivee) {
			etapeCourante = aTraiter.getFirst();
			sommet = etapeCourante.getCourant();
			nbIte++;
			
			suivants = (LinkedList<T>) carte.getSuivant(sommet);
			
			
			aTraiter.remove(etapeCourante);
			
			// Analyse des suivants
			for (T voisin : suivants) {
				if (!(carte.estMarque(voisin)) && !arrivee) {
					
					// Sauvegarde du sommet courant et son prédécesseur
					listeEtapes.add(new Etape<T>(etapeCourante,voisin));
					aTraiter.add(new Etape<T>(etapeCourante,voisin));
					carte.marquer(voisin);
					arrivee = carte.estArrivee(voisin);
					lastVoisin = voisin;
					
				} 
			}
			
		}
		listeEtapes.add(new Etape<T>(etapeCourante,lastVoisin)); //Fin algo : Ajout de l'arrivée au chemin
		
		
		// Fin algo
		// Gestion exc : la carte est non finissable
		if (listeEtapes.getLast().getCourant() == null || !(carte.estArrivee(listeEtapes.getLast().getCourant()))) {
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
		Iterator<T> reverse = chemin.descendingIterator();
		
		while (reverse.hasNext()) {
			cheminReversed.add((T) reverse.next());
		}
		
		timeElapsed = System.nanoTime() - timeStart;
		
		
		
		return new Chemin<T>(nbIte, timeElapsed,(List<T>) cheminReversed);
		
	}	
} 
