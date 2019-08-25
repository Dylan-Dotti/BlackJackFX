import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class GameObject 
	implements IUpdatable, IRenderable, ICollidable {
	private Point3D position;
	private Rectangle2D collider;
	
	public GameObject() {
		this(new Point3D(0, 0, 0));
	}
	
	public GameObject(Point3D position) {
		this.position = position;
	}
	
	public GameObject(Number xPos, Number yPos, Number zPos) {
		this(new Point3D(xPos.doubleValue(), 
				yPos.doubleValue(), zPos.doubleValue()));
	}
	
	public Point3D getPosition() {
		return position;
	}
	
	public void setPosition(Point3D newPos) {
		position = new Point3D(newPos.getX(), 
				newPos.getY(), newPos.getZ());
		if (hasCollider()) {
			collider = new Rectangle2D(position.getX(), position.getY(),
					collider.getWidth(), collider.getHeight());
		}
	}
	
	public void setPosition(Number xPos, Number yPos, Number zPos) {
		setPosition(new Point3D(xPos.doubleValue(), 
				yPos.doubleValue(), zPos.doubleValue()));
	}
	
	public Rectangle2D getCollider() {
		return collider;
	}
	
	public void setCollider(Rectangle2D collider) {
		this.collider = collider;
	}
	
	public void onMouseEnter(MouseEvent mEvent) {
	}
	
	public void onMouseOver(MouseEvent mEvent) {
	}
	
	public void onMouseExit(MouseEvent mEvent) {
	}
	
	public void onMouseClick(MouseEvent mEvent) {
	}

	@Override
	public void render(GraphicsContext gc) {
	}

	@Override
	public void update(long nanoTime) {
	}
}
