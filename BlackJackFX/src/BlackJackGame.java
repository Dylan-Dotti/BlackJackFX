import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlackJackGame extends Scene {
	
	private Canvas gameCanvas;
	private List<GameObject> gameObjects;
	private AnimationTimer timer;
	
	private Button hitButton;
	private Button standButton;
	
	private Deck mainDeck;
	private Hand playerHand;
	
	public BlackJackGame(Pane root) {
		super(root, 1000, 500);
		gameObjects = new ArrayList<>();
		
		gameCanvas = new Canvas(getWidth(), getHeight());
		root.getChildren().add(gameCanvas);
		
		hitButton = new Button("Hit");
		hitButton.setPrefSize(100, 30);
		hitButton.setLayoutX(440 - 
				hitButton.getPrefWidth() / 2);
		hitButton.setLayoutY(450.0 - 
				hitButton.getPrefHeight() / 2);
		hitButton.setOnAction(e -> onHitButtonClicked(e));
		root.getChildren().add(hitButton);
		
		standButton = new Button("Stand");
		standButton.setPrefSize(100, 30);
		standButton.setLayoutX(560.0 -
				standButton.getPrefWidth() / 2.0);
		standButton.setLayoutY(450.0 -
				standButton.getPrefHeight() / 2.0);
		standButton.setOnAction(e -> onStandButtonClicked(e));
		root.getChildren().add(standButton);
	}
	
	public void startGame(Stage primaryStage) {
		BlackJackBoard mainBoard = new BlackJackBoard(
				(int)getWidth(), (int)getHeight());
		mainDeck = new Deck(950, 50, 1);
		mainDeck.shuffle();
		
		playerHand = new Hand(new Point3D(
				250, 300, 1));
		
		gameObjects.add(mainBoard);
		gameObjects.add(mainDeck);
		gameObjects.add(playerHand);
		
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
		gameObjects.forEach(go -> go.update(nanoTime));
	}
	
	private void renderObjects() {
		GraphicsContext gContext = 
				gameCanvas.getGraphicsContext2D();
		gameObjects.stream().sorted((a, b) -> {
			return Double.compare(a.getPosition().getZ(),
					b.getPosition().getZ());
		}).forEach(rObject -> rObject.render(gContext));
	}
	
	public void onHitButtonClicked(ActionEvent aEvent) {
		playerHand.addCard(mainDeck.drawCard(),
				Card.FaceOrientation.FaceUp);
	}
	
	public void onStandButtonClicked(ActionEvent aEvent) {
		
	}
	
	private void OnMouseMoved(MouseEvent mEvent) {
	}
	
	private void OnMouseClick(MouseEvent mEvent) {
	}
}
