import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BlackJackGame extends Scene {
	private Canvas gameCanvas;
	private List<GameObject> gameObjects;
	private AnimationTimer timer;
	
	private Button hitButton;
	private Button standButton;
	
	private Label playerHandValueLabel;
	private Label dealerHandValueLabel;
	
	private Deck mainDeck;
	private Player player;
	private Player dealer;
	
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
		
		player = new Player(250, 350, 1);
		dealer = new Player(250, 200, 1);
		dealer.addCardToHand(mainDeck.drawCard(), 
				Card.FaceOrientation.FaceDown);
		player.addCardToHand(mainDeck.drawCard(),
				Card.FaceOrientation.FaceUp);
		dealer.addCardToHand(mainDeck.drawCard(),
				Card.FaceOrientation.FaceUp);
		player.addCardToHand(mainDeck.drawCard(),
				Card.FaceOrientation.FaceUp);
		
		gameObjects.add(mainBoard);
		gameObjects.add(mainDeck);
		gameObjects.add(player);
		gameObjects.add(dealer);
		
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
	
	private void updateObjects(long nanoTime) {
		gameObjects.forEach(go -> go.update(nanoTime));
		playerHandValueLabel = new Label("Player: " + 
				player.getHand().getHandValue());
		dealerHandValueLabel = new Label("Dealer: " +
				dealer.getHand().getHandValue());
	}
	
	private void renderObjects() {
		GraphicsContext gContext = 
				gameCanvas.getGraphicsContext2D();
		gameObjects.stream().sorted((a, b) -> {
			return Double.compare(a.getPosition().getZ(),
					b.getPosition().getZ());
		}).forEach(rObject -> rObject.render(gContext));
		// draw player hand value
		gContext.setStroke(Color.WHITE);
		gContext.strokeText(playerHandValueLabel.getText(), 475, 400);
		gContext.strokeText(dealerHandValueLabel.getText(), 475, 50);
	}
	
	public void onHitButtonClicked(ActionEvent aEvent) {
		player.addCardToHand(mainDeck.drawCard(),
				Card.FaceOrientation.FaceUp);
	}
	
	public void onStandButtonClicked(ActionEvent aEvent) {
		// play dealer
		hitButton.setDisable(true);
		standButton.setDisable(true);
		new Thread(() -> {
			try {
				dealer.getHand().getCard(0).flipFOrient();
				while (dealer.getHand().getHandValue() < 17) {
					Thread.sleep(1500);
					dealer.addCardToHand(mainDeck.drawCard(),
							Card.FaceOrientation.FaceUp);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
	}
	
	private void OnMouseMoved(MouseEvent mEvent) {
	}
	
	private void OnMouseClick(MouseEvent mEvent) {
	}
}
