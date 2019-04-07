package models;

public class Etape<T> {
	Etape<T> precedent;
	T courant;
	
	public Etape(T cour) {
		this.courant = cour;
	}
	public Etape(Etape<T> pre, T cour) {
		this.precedent = pre;
		this.courant = cour;
	}
	
	public Etape<T> getPrecedent() {
		return this.precedent;
	}
	
	public T getCourant() {
		return this.courant;
	}
	
	
	@Override
	public String toString() {
		return str((int[]) courant);
	}
	
	public String str(int[] t) {
		String s = "Courant : [";
		for(int i : t) {
			s += i + ",";
		}
		return s + ']';
	}
}
