package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.ExceptionArriveeInatteignable;
import exceptions.ExceptionNonScorable;
import exceptions.ExceptionObjectToCoordsTImpossible;
import interfaces.IExplorable;
import interfaces.IScorable;

public class Astar<T> {


	public Chemin rechercher(IExplorable<T> carte) throws ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable, ExceptionNonScorable {
		
		if(carte instanceof IScorable<?>) {
			throw new ExceptionNonScorable();
		}
		
		LinkedList<T> aTraiter = new LinkedList<T>();
		
		// Sauvegarde des parcours de l'algo et de l'origine de chaque sommet (etapes)
		LinkedList<Etape<T>> listeEtapes = new LinkedList<Etape<T>>();
		LinkedList<T> suivants;
		
		// Initialisation algo
		T sommetDepart = carte.getDepart();
		T sommetArrivee = carte.getArrivee();
		T sommet = carte.getDepart();
		Etape<T> etapeCourante = new Etape<T>(sommet);
		Etape<T> etapePrec;
		listeEtapes.add(etapeCourante);
		aTraiter.add(sommet);
		carte.marquer(sommet);
		boolean arrivee = false;
		IScorable<T> scorableCarte = (IScorable<T>) carte;
		
		//Comparateur pour ordonner les noeuds selon leur score
		Comparator<T> leCompareur = new Comparator<T>( ) {

			@Override
			public int compare(T voisin1, T voisin2) {
				double score1 = scorableCarte.calculScore(sommetDepart, voisin1, sommetArrivee);
				double score2 = scorableCarte.calculScore(sommetDepart, voisin2, sommetArrivee);
				
				//pour ordonner du plus gros score au plus petit
				if (score1 > score2) {
					return -1;
				} else if (score1 < score2){
					return 1;
				} else {
					return 0;
				}
			}
			
		};
		
		// Traitement
		while (!aTraiter.isEmpty() && !arrivee) {
			
			etapePrec = etapeCourante;
			sommet = aTraiter.getFirst(); // ?
			aTraiter.remove(sommet);
			
			suivants = (LinkedList<T>) carte.getSuivant(sommet);
			
			// Voisins à ordonné selon le score (Du plus gros au plus petit score)
			ArrayList<T> suivantsOrdoByScore = new ArrayList<T>();
			for (T voisin : suivants) {
				if (!(carte.estMarque(voisin))) {
					suivantsOrdoByScore.add(voisin);
				}
			}
			
			Collections.sort(suivantsOrdoByScore, leCompareur);
			
			//insère les suivants en début de file dans l'ordre du plus gros au plus petit score
			// résultat : liste ordonnée avec les suivants ordonnées du plus petit au plus gros puis les noeuds non parcourus
			for(T voisin : suivantsOrdoByScore) {
				listeEtapes.add(new Etape<T>(etapePrec,voisin));
				aTraiter.push(voisin);
				carte.marquer(voisin);
				arrivee = carte.estArrivee(voisin);
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
