import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

//change to extend scene?
public class BlackJackGame extends Canvas {
	
	private BlackJackBoard mainBoard;
	private Deck mainDeck;
	
	private AnimationTimer timer;
	
	private Card topCard;
	
	public BlackJackGame() {
		super();
		setWidth(1000);
		setHeight(500);
	}
	
	public void startGame(Stage primaryStage) {
		System.out.println("Starting game");
		mainBoard = new BlackJackBoard(
				(int)getWidth(), (int)getHeight());
		mainDeck = new Deck();
		mainDeck.shuffle();
		
		topCard = mainDeck.drawCard();
		topCard.setPosition(500, 250);
		
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
		GraphicsContext gContext = getGraphicsContext2D();
		mainBoard.render(gContext);
		mainDeck.render(gContext);
		topCard.render(gContext);
	}
}
