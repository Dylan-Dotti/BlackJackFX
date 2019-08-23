import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> cards;
	
	public Deck() {
		generateDeck();
		System.out.println("Deck created");
	}
	
	public void generateDeck() {
		cards = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}
	
	public int getNumCardsRemaining() {
		return cards.size();
	}
	
	public void shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < cards.size(); i++) {
			int randIndex = rand.nextInt(cards.size());
			Card tempCard = cards.get(i);
			cards.set(i, cards.get(randIndex));
			cards.set(randIndex, tempCard);
		}
	}
	
	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("Deck is empty");
		}
		return cards.remove(0);
	}
	
	public void printDeck() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(String.format("%s: %s",
					i, cards.get(i).toString()));
		}
	}
}
