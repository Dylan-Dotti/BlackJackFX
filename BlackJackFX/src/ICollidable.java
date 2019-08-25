import javafx.geometry.Rectangle2D;

public interface ICollidable {
	public Rectangle2D getCollider();
	public void setCollider(Rectangle2D collider);
	public default boolean hasCollider() {
		return getCollider() != null;
	}
}
