import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlackJackGame extends Scene {
	
	private Canvas gameCanvas;
	
	private BlackJackBoard mainBoard;
	private Deck mainDeck;
	
	private AnimationTimer timer;
	
	private Card topCard;
	
	public BlackJackGame(Pane root) {
		super(root, 1000, 500);
		gameCanvas = new Canvas(getWidth(), getHeight());
		root.getChildren().add(gameCanvas);
	}
	
	public void startGame(Stage primaryStage) {
		mainBoard = new BlackJackBoard(
				(int)getWidth(), (int)getHeight());
		mainDeck = new Deck();
		mainDeck.shuffle();
		
		topCard = mainDeck.drawCard();
		topCard.setPosition(500, 250);
		topCard.flipFOrient();
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long nanoTime) {
				updateObjects(nanoTime);
				renderObjects();
			}
		};
		timer.start();
	}
	
	public void pauseGame() {
		timer.stop();
	}
	
	public void resumeGame() {
		timer.start();
	}
	
	private void updateObjects(long nanoTime) {
		mainBoard.update(nanoTime);
		mainDeck.update(nanoTime);
		topCard.update(nanoTime);
	}
	
	private void renderObjects() {
		GraphicsContext gContext = 
				gameCanvas.getGraphicsContext2D();
		mainBoard.render(gContext);
		mainDeck.render(gContext);
		topCard.render(gContext);
	}
}
