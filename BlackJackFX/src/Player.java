import javafx.geometry.Point3D;

public class Player extends GameObject {
	private Hand hand;
	
	public Player() {
		setPosition(250, 300, 1);
		hand = new Hand(new Point3D(250, 300, 1));
		addChildObject(hand);
	}
	
	public boolean addCardToHand(Card card,
			Card.FaceOrientation fOrient) {
		return hand.addCard(card, fOrient);
	}
	
	public boolean removeCardFromHand(Card card) {
		return hand.removeCard(card);
	}
}
