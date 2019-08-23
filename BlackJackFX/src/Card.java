import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends GameObject {
	
	public static final double ASPECT_RATIO = 0.7998;
	public static final double CARD_HEIGHT = 100;
	public static final double CARD_WIDTH = CARD_HEIGHT * ASPECT_RATIO;
	
	private Rank rank;
	private Suit suit;
	private ImageView iv;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.iv = loadImageView(rank, suit);
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public ImageView getImageView() {
		return iv;
	}
	
	public int getCardValue() {
		return -1;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(iv.getImage(), getPosition().getX(), 
				getPosition().getY(), iv.getFitWidth(), iv.getFitHeight());
	}
	
	@Override
	public String toString() {
		return String.format("%s of %s", getRank(), getSuit());
	}
	
	private ImageView loadImageView(Rank rank, Suit suit) {
		String imageURL = String.format("file:img/%s/%s_%s.png", suit, rank, suit);
		System.out.println("Loading image: " + imageURL);
		ImageView iv = new ImageView(new Image(imageURL));
		iv.setFitWidth(CARD_WIDTH);
		iv.setFitHeight(CARD_HEIGHT);
		return iv;
	}
}
