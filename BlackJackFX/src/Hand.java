import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;

public class Hand extends GameObject {
	
	private List<Card> handCards;
	
	private int spacing;
	private Direction growDirection;
	
	public Hand() {
		this(new Point3D(0, 0, 0), Direction.Right);
	}
	
	public Hand(Point3D position) {
		this(position, Direction.Right);
	}
	
	public Hand(Point3D position, Direction growDirection) {
		super(position);
		handCards = new ArrayList<>();
		spacing = 40;
		this.growDirection = growDirection;
	}
	
	public Card getCard(int index) {
		return handCards.get(index);
	}
	
	public boolean addCard(Card card, Card.FaceOrientation fOrient) {
		if (handCards.add(card)) {
			double xPos = getPosition().getX() + handCards.size() *
					(growDirection == Direction.Left ?
					 -spacing : (growDirection == Direction.Right ?
					 spacing : 0));
			double yPos = getPosition().getY() + handCards.size() *
					(growDirection == Direction.Up ?
					 -spacing : (growDirection == Direction.Down ?
					 spacing : 0));
			double zPos = handCards.size() + 1;
			card.setPosition(xPos - Card.CARD_WIDTH / 2,
					yPos - Card.CARD_HEIGHT / 2, zPos);
			card.setFOrient(fOrient);
			addChildObject(card);
			return true;
		}
		return false;
	}
	
	public boolean removeCard(Card card) {
		if (handCards.remove(card)) {
			return removeChildObject(card);
		}
		return false;
	}
	
	public void removeAllCards() {
		handCards.clear();
	}
	
	public int getHandValue() {
		// init handValue to value of non-aces
		int handValue = handCards.stream().filter(
				c -> c.getRank() != Rank.Ace && c.getFOrient() == 
				Card.FaceOrientation.FaceUp).mapToInt(c -> {
					switch (c.getRank()) {
					case Two:
						return 2;
					case Three:
						return 3;
					case Four:
						return 4;
					case Five:
						return 5;
					case Six:
						return 6;
					case Seven:
						return 7;
					case Eight:
						return 8;
					case Nine:
						return 9;
					case Ten:
					case Jack:
					case Queen:
					case King:
						return 10;
					default:
						return -1;
					}
				}).sum();
		long acesCount = handCards.stream().
				filter(c -> c.getRank() == Rank.Ace && 
				c.getFOrient() == Card.FaceOrientation.FaceUp).count();
		handValue += 11 * acesCount;
		for (int i = 0; i < acesCount; i++) {
			if (handValue > 21) {
				handValue -= 10;
			}
		}
		return handValue;
	}
	
	public static void testHand() {
		//test 1
		Hand hand = new Hand();
		hand.addCard(new Card(Rank.Ace, Suit.Spades),
				Card.FaceOrientation.FaceUp);
		System.out.println("Test 1: " + (hand.getHandValue() == 11 ?
				"Passed" : "Failed"));
		//test 2
		hand = new Hand();
		hand.addCard(new Card(Rank.Ace, Suit.Spades),
				Card.FaceOrientation.FaceUp);
		hand.addCard(new Card(Rank.Ace, Suit.Hearts),
				Card.FaceOrientation.FaceUp);
		System.out.println("Test 2: " + (hand.getHandValue() == 12 ?
				"Passed" : "Failed"));
		//test 3
		hand = new Hand();
		hand.addCard(new Card(Rank.Ace, Suit.Spades),
				Card.FaceOrientation.FaceUp);
		hand.addCard(new Card(Rank.King, Suit.Hearts),
				Card.FaceOrientation.FaceUp);
		System.out.println("Test 3: " + (hand.getHandValue() == 21 ?
				"Passed" : "Failed"));
	}
}
