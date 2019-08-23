import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlackJackBoard extends Canvas implements IUpdatable {
	private List<GameObject> boardObjects;
	
	public BlackJackBoard() {
		super();
		boardObjects = new ArrayList<>();
		setWidth(1000);
		setHeight(500);
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.DARKGREEN);
		gc.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void update(double gameTimeNano) {
		for (GameObject go : boardObjects) {
			go.update(gameTimeNano);
		}
	}
}
