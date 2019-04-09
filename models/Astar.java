package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.ExceptionNonScorable;
import exceptions.ExceptionObjectToCoordsTImpossible;
import interfaces.IExplorable;
import interfaces.IScorable;

/**
 * Classe pour gérer la recherche avec l'algorithme A*
 * @author Tuc
 *
 * @param <T>
 */
public class Astar<T> {

	int distanceDepuisDepart = 0;

	/**
	 * Algo de recherche A* générique
	 * Nécessite une carte implémentant IExplorable et IScorable
	 * @param carte
	 * @return le chemin
	 * @throws ExceptionObjectToCoordsTImpossible
	 * @throws ExceptionArriveeInatteignable
	 * @throws ExceptionNonScorable
	 */
	public Chemin rechercher(IExplorable<T> carte) throws ExceptionObjectToCoordsTImpossible, ExceptionArriveeInatteignable, ExceptionNonScorable {
		
		if(!(carte instanceof IScorable<?>)) {
			throw new ExceptionNonScorable();
		}
		
		LinkedList<Etape<T>> aTraiter = new LinkedList<Etape<T>>();
		int nbIterations = 0;
		long timeStart,timeElapsed;
		timeStart = System.nanoTime();
		// Sauvegarde des parcours de l'algo et de l'origine de chaque sommet (etapes)
		LinkedList<Etape<T>> listeEtapes = new LinkedList<Etape<T>>();
		LinkedList<T> suivants;
		
		// Initialisation algo
		T sommetDepart = carte.getDepart();
		T sommetArrivee = carte.getArrivee();
		T sommet = carte.getDepart();
		Etape<T> etapeCourante = new Etape<T>(null,sommet);
		listeEtapes.add(etapeCourante);
		aTraiter.add(etapeCourante);
		carte.marquer(sommet);
		boolean arrivee = false;
		IScorable<T> scorableCarte = (IScorable<T>) carte;
		
		T lastVoisin = null; // Tampon du dernier voisin analysé
		
		//pour comparer 2 noeuds selon leur distance à l'arrivée
		//Score les plus grands en premier
		Comparator<T> leCompareur = new Comparator<T>( ) {

			@Override
			public int compare(T voisin1, T voisin2) {
				
				double score1 = scorableCarte.calculScore(distanceDepuisDepart, voisin1, sommetArrivee);
				double score2 = scorableCarte.calculScore(distanceDepuisDepart, voisin2, sommetArrivee);
				
				//System.out.println("score1 " + score1 + "  score2 " + score2);
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
			nbIterations++;
			etapeCourante = aTraiter.getLast();
			sommet = etapeCourante.getCourant();
			aTraiter.remove(etapeCourante);
			
			suivants = (LinkedList<T>) carte.getSuivant(sommet);
			
			// Voisins à ordonné selon le score (Du plus gros au plus petit score)
			ArrayList<T> suivantsOrdoByScore = new ArrayList<T>();
			for (T voisin : suivants) {
				if (!(carte.estMarque(voisin))) {
					suivantsOrdoByScore.add(voisin);
				}
			}
			
			//ordonne la liste par Score DÉCROISSANTS
			Collections.sort(suivantsOrdoByScore, leCompareur);
			
			//insère les suivants en début de file dans l'ordre du plus gros au plus petit score
			//résultat : liste ordonnée avec les suivants ordonnées du plus petit au plus gros puis les noeuds non parcourus
			if (!suivantsOrdoByScore.isEmpty()) {

				distanceDepuisDepart++;
				//System.out.println(carte.toString());
				for(T voisin : suivantsOrdoByScore) {

					if (!arrivee) {
						
						listeEtapes.add(new Etape<T>(etapeCourante,voisin));
						aTraiter.push(new Etape<T>(etapeCourante,voisin));
						carte.marquer(voisin);
						arrivee = carte.estArrivee(voisin);
						lastVoisin = voisin;
					}
				}
			}
			
		}
		
		listeEtapes.add(new Etape<T>(etapeCourante,lastVoisin)); //Fin algo : Ajout de l'arrivée au chemin
		
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
		Iterator<T> reverse = chemin.descendingIterator();
		
		while (reverse.hasNext()) {
			cheminReversed.add((T) reverse.next());
		}
		timeElapsed = System.nanoTime() - timeStart;
		return new Chemin<T>(nbIterations,timeElapsed,(List<T>) cheminReversed);
		
	}
	

} 
