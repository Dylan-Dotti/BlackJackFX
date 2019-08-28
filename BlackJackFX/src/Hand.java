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
		int handValue = 0;
		for (Card card : handCards) {
			if (card.getFOrient() == Card.FaceOrientation.FaceUp) {
				handValue += card.getCardValue(handCards);
			}
		}
		return handValue;
	}
}
