import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlackJackBoard extends GameObject implements IRenderable {
	private int width, height;
	
	public BlackJackBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.DARKGREEN);
		gc.fillRect(0, 0, getWidth(), getHeight());
	}
}
