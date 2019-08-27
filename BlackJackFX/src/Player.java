import javafx.geometry.Point3D;

public class Player extends GameObject {
	private Hand hand;
	
	public Player() {
		this(new Point3D(0, 0, 1));
	}
	
	public Player(Number xPos, Number yPos, Number zPos) {
		this(new Point3D(xPos.doubleValue(), 
				yPos.doubleValue(), zPos.doubleValue()));
	}
	
	public Player(Point3D position) {
		setPosition(position);
		hand = new Hand(getPosition());
		addChildObject(hand);
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean addCardToHand(Card card,
			Card.FaceOrientation fOrient) {
		return hand.addCard(card, fOrient);
	}
	
	public boolean removeCardFromHand(Card card) {
		return hand.removeCard(card);
	}
}
