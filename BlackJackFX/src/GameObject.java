import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject implements IUpdatable, IRenderable {
	private Point2D position;
	private Rectangle2D collider;
	
	public GameObject() {
		position = new Point2D(0, 0);
	}
	
	public GameObject(Point2D position) {
		this.position = position;
	}
	
	public GameObject(Number xPos, Number yPos) {
		this.position = new Point2D(
				xPos.doubleValue(), yPos.doubleValue());
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Number xPos, Number yPos) {
		position = new Point2D(
				xPos.doubleValue(), yPos.doubleValue());
	}
	
	public void setPosition(Point2D newPos) {
		position = new Point2D(newPos.getX(), newPos.getY());
	}
	
	public Rectangle2D getCollider() {
		return collider;
	}

	@Override
	public void render(GraphicsContext gc) {
	}

	@Override
	public void update(long nanoTime) {
	}
	
	
}
