import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class RectangleCollider {
	private Point2D position;
	private int width, height;
	
	public RectangleCollider(int xPos, int yPos, 
			int width, int height) {
		this.position = new Point2D(xPos, yPos);
		this.width = width;
		this.height = height;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public Rectangle2D getCollisionBox() {
		return new Rectangle2D(position.getX(), 
				position.getY(), width, height);
	}
}
