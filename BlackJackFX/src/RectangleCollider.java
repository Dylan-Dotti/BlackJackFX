import javafx.geometry.Rectangle2D;

public class RectangleCollider {
	private Vector2 position;
	private int width, height;
	
	public RectangleCollider(int xPos, int yPos, 
			int width, int height) {
		this.position = new Vector2(xPos, yPos);
		this.width = width;
		this.height = height;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle2D getCollisionBox() {
		return new Rectangle2D(position.x, position.y, width, height);
	}
}
