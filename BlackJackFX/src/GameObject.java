import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public abstract class GameObject implements IRenderable {
	private Point2D position;
	private Rectangle2D collider;
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(int xPos, int yPos) {
		position = new Point2D(xPos, yPos);
	}
	
	public void setPosition(Point2D newPos) {
		position = new Point2D(newPos.getX(), newPos.getY());
	}
	
	public Rectangle2D getCollider() {
		return collider;
	}
}
