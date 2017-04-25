package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	    printList(deckRear);
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		CardNode prev = deckRear;
		CardNode ptr = deckRear.next;
		CardNode n;
		
		if(deckRear == null)
			return;
		
		do{
			
			if(ptr.cardValue == 27){
				if(ptr.next == deckRear){
					n = ptr;
					deckRear = n;
					prev.next = ptr.next;
					ptr.next = ptr.next.next;
					prev.next.next = ptr;
					break;
					
				}
				if(ptr == deckRear){
					n = ptr;
					deckRear = n.next;
					prev.next = ptr.next;
					ptr.next = ptr.next.next;
					prev.next.next = ptr;
					break;
					
				}
				else
				prev.next = ptr.next;
				ptr.next = ptr.next.next;
				prev.next.next = ptr;
				
				break;
			}
			ptr = ptr.next;
			prev = prev.next;
			
			
			
			
		}while(prev != deckRear);
		printList(deckRear);
		return;
		
		/*do{
			if(prev.cardValue == 27){
				int tmp = ptr.cardValue;
				ptr.cardValue = 27;
				prev.cardValue = tmp;
				
				
			}
			else{
				prev = ptr;
				ptr = ptr.next;
			}
			
		}
		while(prev!=deckRear);
		
		return;
		*/
			
		
		
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD
		CardNode prev = deckRear;
		CardNode ptr = deckRear.next;
		CardNode n;
		
		if(deckRear == null)
			return;
		do{
			if(ptr.cardValue == 28){
				
				if(ptr == deckRear){
					n = ptr;
					deckRear = n.next;
					prev.next = ptr.next;
					ptr.next = ptr.next.next.next;
					prev.next.next.next = ptr;
					break;
				}
				if(ptr.next == deckRear){
					n = ptr;
					deckRear = n.next.next;
					prev.next = ptr.next;
					ptr.next = ptr.next.next.next;
					prev.next.next.next = ptr;
					break;
					
				}
				if(ptr.next.next==deckRear){
					n = ptr;
					deckRear = n;
					prev.next = ptr.next;
					ptr.next = ptr.next.next.next;
					prev.next.next.next = ptr;
					break;
				}
				else 
				prev.next = ptr.next;
				ptr.next = ptr.next.next.next;
				prev.next.next.next = ptr;
				
				break;
				
					
			}
			
			ptr = ptr.next;
			prev = prev.next;
			
		}while(prev!=deckRear);
		printList(deckRear);
		return;
		
		
		
		
		
		/*do{
			if(prev.cardValue == 28){
				int tmp = prev.cardValue;
				prev.cardValue = prev.next.cardValue;
				prev.next.cardValue = ptr.cardValue;
				ptr.cardValue = tmp;
			}
			else{
				prev = prev.next;
				ptr = ptr.next;
			}
			
		}while(prev!= deckRear);
		
		return;
		*/
		
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		CardNode front = deckRear.next;
		CardNode ptrA = deckRear;
		CardNode ptrB;
		CardNode jokerA;
		CardNode jokerB;
		if(deckRear == null)
			return;
		do{
			if(ptrA.next.cardValue == 27 || ptrA.next.cardValue == 28){
				break;
			}
			else{
				ptrA = ptrA.next;
			}
			//this sets the pointer before the first joker
			
		}while(ptrA != deckRear);
		
		ptrB = ptrA.next.next;
		do{
			if(ptrB.cardValue == 27 || ptrB.cardValue == 28){
				
				break;
				
				
			}
			else{
				ptrB = ptrB.next;
				
			}
			//this finds the pointer after the second joker
		}while(ptrB != deckRear );
		CardNode rear = deckRear;
		
		jokerB = ptrB;
		ptrB = ptrB.next;
		jokerA = ptrA.next;
		if(jokerB == deckRear && jokerA == front){
			printList(deckRear);
			return;
		}
		if(jokerB == deckRear){
			deckRear = ptrA;
			printList(deckRear);
			return;
			
		}
		if(jokerA == front){
			deckRear = jokerB;
			printList(deckRear);
			return;
		}
		else
		jokerB.next = front;
		rear.next = jokerA;
		ptrA.next = ptrB;
		deckRear = ptrA;
		
		printList(deckRear);
		return;
		
		
		
		
		
		
	}
		
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		if(deckRear == null) return;
		CardNode ptr = deckRear.next;
		int movespace = deckRear.cardValue;
		int count = 1;
		CardNode front = deckRear.next;
		CardNode end = deckRear.next;
		if(movespace == 28 || movespace == 27){
			printList(deckRear);
			return;
		}
		do{
			if(ptr.next == deckRear){
				break;
			}
			ptr = ptr.next;
			 //finds node before deckRear
		}while(ptr != deckRear);
		
		while(count != movespace){
			end = end.next;
			count ++;
		}
		deckRear.next = end.next;
		ptr.next = front;
		end.next = deckRear;
		printList(deckRear);
		return;
		
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		CardNode ptr = deckRear;
		int count = deckRear.next.cardValue;
		
		if(count == 28) count = 27;
		
		for(int i = 0; i < count; i++){
			ptr = ptr.next;
		}
	    
		while(ptr.cardValue == 27 || ptr.cardValue == 28){
	    	jokerA();
	    	jokerB();
	    	tripleCut();
	    	countCut();
	    	ptr = deckRear;
	    	count = deckRear.next.cardValue;
	    	if(count == 28) count = 27;
	    	for(int n = 0; n < count; n++){
	    		ptr = ptr.next;
	    	}
	    }
		
	    System.out.print(ptr.cardValue);
		return ptr.cardValue;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		String letter = "";
		
		for (int i=0; i<message.length(); i++){
			if (Character.isLetter(message.charAt(i))){
				//length++;
				Character.toUpperCase(message.charAt(i));
				letter += message.charAt(i);
			}			
		}		
		
		String encrypted = "";
		for (int i=0; i<letter.length(); i++){
			int num = (letter.charAt(i)-64)+(getKey());
			if (num > 26){
				num -= 26;
			}
			num += 64;
			encrypted += (char)num;
		}
		return encrypted;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		String letter = "";
		
		for (int i=0; i<message.length(); i++){
			if (Character.isLetter(message.charAt(i))){
				//length++;
				Character.toUpperCase(message.charAt(i));
				letter += message.charAt(i);
			}			
		}	
		
		String decrypted = "";
		for (int i=0; i<letter.length(); i++){
			
			int decryptNum = (int)letter.charAt(i);
			decryptNum -= 64;
			decryptNum -= getKey();
			if (decryptNum <= 0){
				decryptNum += 26;
			}
			decryptNum += 64;
			decrypted += (char)decryptNum;
		}
		return decrypted;
		
	
	}
}
