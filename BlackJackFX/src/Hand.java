import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;
import javafx.scene.canvas.GraphicsContext;

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
		spacing = 30;
		this.growDirection = growDirection;
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
			return true;
		}
		return false;
	}
	
	public boolean removeCard(Card card) {
		return handCards.remove(card);
	}
	
	public void removeAllCards() {
		handCards.clear();
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		handCards.forEach(c -> c.render(gc));
	}
}
