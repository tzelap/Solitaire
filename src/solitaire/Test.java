package solitaire;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solitaire test = new Solitaire ();
		System.out.print("RandomD: ");test.makeDeck();
		System.out.print("Joker A: ");test.jokerA();
		System.out.print("Joker B: ");test.jokerB();
		System.out.print("TripleC: ");test.tripleCut();
		System.out.print("CountCu: ");test.countCut();
		System.out.print("Key: ");test.getKey();		
	}

}
