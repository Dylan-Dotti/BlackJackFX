import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlackJackGame extends Scene {
	
	private Canvas gameCanvas;
	
	private AnimationTimer timer;
	
	private BlackJackBoard mainBoard;
	private Deck mainDeck;
	private List<GameObject> gameObjects;
	
	private Card topCard;
	
	public BlackJackGame(Pane root) {
		super(root, 1000, 500);
		gameObjects = new ArrayList<>();
		
		gameCanvas = new Canvas(getWidth(), getHeight());
		root.getChildren().add(gameCanvas);
		
		Button hitButton = new Button("Hit");
		hitButton.setPrefSize(100, 30);
		hitButton.setLayoutX(440 - 
				hitButton.getPrefWidth() / 2);
		System.out.println(hitButton.getLayoutX());
		hitButton.setLayoutY(450.0 - 
				hitButton.getPrefHeight() / 2);
		root.getChildren().add(hitButton);
		
		Button standButton = new Button("Stand");
		standButton.setPrefSize(100, 30);
		standButton.setLayoutX(560.0 -
				standButton.getPrefWidth() / 2.0);
		standButton.setLayoutY(450.0 -
				standButton.getPrefHeight() / 2.0);
		root.getChildren().add(standButton);
	}
	
	public void startGame(Stage primaryStage) {
		mainBoard = new BlackJackBoard(
				(int)getWidth(), (int)getHeight());
		mainDeck = new Deck();
		mainDeck.shuffle();
		
		topCard = mainDeck.drawCard();
		topCard.setPosition(500, 250, 1);
		topCard.flipFOrient();
		
		gameObjects.add(mainBoard);
		gameObjects.add(mainDeck);
		gameObjects.add(topCard);
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long nanoTime) {
				updateObjects(nanoTime);
				renderObjects();
			}
		};
		timer.start();
		
		this.setOnMouseMoved(e -> OnMouseMoved(e));
		this.setOnMouseClicked(e -> OnMouseClick(e));
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
		gameObjects.stream().sorted((a, b) -> {
			return Double.compare(a.getPosition().getZ(),
					b.getPosition().getZ());
		}).forEach(rObject -> rObject.render(gContext));
	}
	
	private void OnMouseMoved(MouseEvent mEvent) {
		
	}
	
	private void OnMouseClick(MouseEvent mEvent) {
		System.out.println(String.format("Mouse click at (%s, %s)", 
				mEvent.getX(), mEvent.getY()));
	}
}
