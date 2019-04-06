package models;

public class Etape<T> {
	Etape<T> precedent;
	T courant;
	
	public Etape(T cour) {
		this.courant = cour;
	}
	public Etape(Etape<T> pre, T cour) {
		this.precedent = pre;
		this.courant = courant;
	}
	
	public Etape<T> getPrecedent() {
		return this.precedent;
	}
	
	public T getCourant() {
		return this.courant;
	}
}
