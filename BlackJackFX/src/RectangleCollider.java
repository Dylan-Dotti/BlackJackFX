import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class RectangleCollider {
	private Rectangle2D collider;
	
	public RectangleCollider(int xPos, int yPos, 
			int width, int height) {
		collider = new Rectangle2D(xPos, yPos, width, height);
	}
	
	public Point2D getPosition() {
		return new Point2D(collider.getMinX(), 
				collider.getMinY());
	}
	
	public Rectangle2D getCollisionBox() {
		return collider;
	}
}
