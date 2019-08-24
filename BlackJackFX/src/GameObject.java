import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject implements IUpdatable, IRenderable {
	private Point3D position;
	private Rectangle2D collider;
	
	public GameObject() {
		position = new Point3D(0, 0, 0);
	}
	
	public GameObject(Point3D position) {
		this.position = position;
	}
	
	public GameObject(Number xPos, Number yPos, Number zPos) {
		this.position = new Point3D(xPos.doubleValue(), 
				yPos.doubleValue(), zPos.doubleValue());
	}
	
	public Point3D getPosition() {
		return position;
	}
	
	public void setPosition(Number xPos, Number yPos, Number zPos) {
		position = new Point3D(xPos.doubleValue(), 
				yPos.doubleValue(), zPos.doubleValue());
	}
	
	public void setPosition(Point3D newPos) {
		position = new Point3D(newPos.getX(), 
				newPos.getY(), newPos.getZ());
	}
	
	public boolean hasCollider() {
		return collider != null;
	}
	
	public Rectangle2D getCollider() {
		return collider;
	}
	
	public void setCollider(Rectangle2D collider) {
		this.collider = collider;
	}

	@Override
	public void render(GraphicsContext gc) {
	}

	@Override
	public void update(long nanoTime) {
	}
}
