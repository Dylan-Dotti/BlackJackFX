import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMain extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Pane rootPane = new Pane();
		BlackJackGame game = new BlackJackGame(rootPane);
		
		primaryStage.setTitle("BlackJack");
		primaryStage.setScene(game);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		
		game.startGame();
		primaryStage.show();
	}
	
	private ImageView loadCard(Rank rank, Suit suit) {
		String imageURL = String.format(
				"file:img/%s/%s_%s.png", suit, rank, suit);
		System.out.println("Loading image: " + imageURL);
		return new ImageView(new Image(imageURL));
	}
	
	private ImageView loadCard(Rank rank, Suit suit, 
			double width, double height) {
		ImageView cardIV = loadCard(rank, suit);
		cardIV.setFitWidth(width);
		cardIV.setFitHeight(height);
		return cardIV;
	}
	
	private Parent getCardsDisplay() {
		VBox vBoxRoot = new VBox();
		HBox[] hBoxRows = new HBox[4];
		int iRow = 0;
		for (Suit suit : Suit.values()) {
			hBoxRows[iRow] = new HBox();
			for (Rank rank : Rank.values()) {
				ImageView cardIV = loadCard(rank, suit, 
						Card.CARD_WIDTH, Card.CARD_HEIGHT);
				hBoxRows[iRow].getChildren().add(cardIV);
			}
			iRow++;
		}
		vBoxRoot.getChildren().addAll(hBoxRows);
		return vBoxRoot;
	}
}
