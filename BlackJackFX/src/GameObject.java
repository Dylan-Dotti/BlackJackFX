import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class GameObject 
	implements IUpdatable, IRenderable, ICollidable {
	private Point3D position;
	private Rectangle2D collider;
	
	private List<GameObject> childObjects;
	
	public GameObject() {
		this(new Point3D(0, 0, 0));
	}
	
	public GameObject(Point3D position) {
		this.position = position;
		childObjects = new ArrayList<>();
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
		// update collider
		if (hasCollider()) {
			collider = new Rectangle2D(position.getX(), position.getY(),
					collider.getWidth(), collider.getHeight());
		}
		// set position of child objects
		childObjects.forEach(obj -> {
			Point3D objPos = obj.getPosition();
			Point3D offset = new Point3D(
					objPos.getX() - getPosition().getX(), 
					objPos.getY() - getPosition().getY(),
					objPos.getZ() - getPosition().getZ());
			obj.setPosition(new Point3D(
					newPos.getX() + offset.getX(),
					newPos.getY() + offset.getY(),
					newPos.getZ() + offset.getZ()));
		});
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
	
	public boolean addChildObject(GameObject go) {
		return childObjects.add(go);
	}
	
	public boolean removeChildObject(GameObject go) {
		return childObjects.remove(go);
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
		childObjects.forEach(obj -> obj.render(gc));
	}

	@Override
	public void update(long nanoTime) {
		childObjects.forEach(obj -> obj.update(nanoTime));
	}
}
