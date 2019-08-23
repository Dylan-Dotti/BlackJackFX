import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> activeCards;
	
	public Player() {
		activeCards = new ArrayList<>(6);
	}
	
	public boolean addActiveCard(Card card) {
		return activeCards.add(card);
	}
	
	public boolean removeActiveCard(Card card) {
		return activeCards.remove(card);
	}
}
