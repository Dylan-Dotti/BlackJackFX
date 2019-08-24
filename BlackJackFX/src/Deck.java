import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class Deck extends GameObject implements IRenderable {
	private List<Card> cards;
	
	public Deck() {
		super();
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
	
	public Card getCard(Rank rank, Suit suit) {
		for (Card card : cards) {
			if (card.hasRankAndSuit(rank, suit)) {
				cards.remove(card);
				return card;
			}
		}
		return null;
	}
	
	public void shuffle() {
		Random rand = new Random();
		for (int i = 0; i < cards.size(); i++) {
			int randIndex = rand.nextInt(cards.size());
			Card tempCard = cards.get(i);
			cards.set(i, cards.get(randIndex));
			cards.set(randIndex, tempCard);
		}
	}
	
	public Card drawCard() {
		return cards.isEmpty() ? null : cards.remove(0);
	}
	
	public Card peekTopCard() {
		return cards.isEmpty() ? null : cards.get(0);
	}
	
	public void printDeck() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(String.format("%s: %s",
					i, cards.get(i).toString()));
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		Card topCard = peekTopCard();
		if (topCard != null) {
			gc.drawImage(topCard.getRenderImageView().getImage(), 
					getPosition().getX(), getPosition().getY(), 
					Card.CARD_WIDTH, Card.CARD_HEIGHT);
		}
	}
}
