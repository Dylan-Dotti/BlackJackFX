import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends GameObject {
	
	public enum FaceOrientation { FaceUp, FaceDown };
	
	public static final double ASPECT_RATIO = 0.7998;
	public static final double CARD_HEIGHT = 100;
	public static final double CARD_WIDTH = CARD_HEIGHT * ASPECT_RATIO;
	
	private static ImageView backIV;
	
	private Rank rank;
	private Suit suit;
	private FaceOrientation fOrient;
	private ImageView frontIV;
	
	static {
		loadCardBackImage();
	}
	
	public Card(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
		this.fOrient = FaceOrientation.FaceDown;
		loadCardFrontImage(rank, suit);
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public FaceOrientation getFOrient() {
		return fOrient;
	}
	
	public void setFOrient(FaceOrientation fOrient) {
		this.fOrient = fOrient;
	}
	
	public void flipFOrient() {
		fOrient = fOrient == FaceOrientation.FaceUp ?
				FaceOrientation.FaceDown : FaceOrientation.FaceUp;
	}
	
	public ImageView getFrontImageView() {
		return frontIV;
	}
	
	public ImageView getBackImageView() {
		return backIV;
	}
	
	public ImageView getRenderImageView() {
		return fOrient == FaceOrientation.FaceUp ?
				frontIV : backIV;
	}
	
	public int getCardValue() {
		return -1;
	}
	
	public boolean hasRankAndSuit(Rank rank, Suit suit) {
		return this.rank == rank && this.suit == suit;
	}
	
	@Override
	public void update(long nanoTime) {
		super.update(nanoTime);
		setPosition(getPosition().getX() + 1, getPosition().getY());
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(getRenderImageView().getImage(), 
				getPosition().getX(), getPosition().getY(), 
				frontIV.getFitWidth(), frontIV.getFitHeight());
	}
	
	@Override
	public String toString() {
		return String.format("%s of %s", getRank(), getSuit());
	}
	
	private void loadCardFrontImage(Rank rank, Suit suit) {
		String imageURL = String.format(
				"file:img/%s/%s_%s.png", suit, rank, suit);
		System.out.println("Loading image: " + imageURL);
		frontIV = new ImageView(new Image(imageURL));
		frontIV.setFitWidth(CARD_WIDTH);
		frontIV.setFitHeight(CARD_HEIGHT);
	}
	
	private static void loadCardBackImage() {
		backIV = new ImageView(new Image("file:img/card_back_2.jpg"));
		System.out.println("Card back: " + backIV.getImage());
		backIV.setFitWidth(CARD_WIDTH);
		backIV.setFitHeight(CARD_HEIGHT);
	}
}
