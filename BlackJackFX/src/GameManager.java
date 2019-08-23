import javafx.stage.Stage;

public class GameManager {
	
	private BlackJackBoard mainBoard;
	private Deck mainDeck;
	
	public void startGame(Stage primaryStage) {
		mainBoard = new BlackJackBoard();
		mainDeck = new Deck();
	}
}
