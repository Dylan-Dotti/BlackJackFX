import java.util.List;
import java.util.stream.Collectors;

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
	
	public int getCardValue(List<Card> handCards) {
		switch(rank) {
		case Ace:
			List<Card> aces = handCards.stream().
				filter(c -> c.rank == Rank.Ace).
				collect(Collectors.toList());
			List<Card> nonAces = handCards.stream().
				filter(c -> c.rank != Rank.Ace).
				collect(Collectors.toList());
			int nonAcesValue = nonAces.stream().mapToInt(
					c -> c.getCardValue(nonAces)).sum();
			// calculate all possible combinations
			return 11;
		case Two:
			return 2;
		case Three:
			return 3;
		case Four:
			return 4;
		case Five:
			return 5;
		case Six:
			return 6;
		case Seven:
			return 7;
		case Eight:
			return 8;
		case Nine:
			return 9;
		case Ten:
		case Jack:
		case Queen:
		case King:
			return 10;
		default:
			return -1;
		}
	}
	
	public boolean hasRankAndSuit(Rank rank, Suit suit) {
		return this.rank == rank && this.suit == suit;
	}
	
	@Override
	public void update(long nanoTime) {
		super.update(nanoTime);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		ImageView renderIV = getRenderImageView();
		gc.drawImage(renderIV.getImage(), 
				getPosition().getX() - renderIV.getFitWidth() / 2,
				getPosition().getY() - renderIV.getFitWidth() / 2, 
				renderIV.getFitWidth(), renderIV.getFitHeight());
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
		backIV.setFitWidth(CARD_WIDTH);
		backIV.setFitHeight(CARD_HEIGHT);
	}
}
