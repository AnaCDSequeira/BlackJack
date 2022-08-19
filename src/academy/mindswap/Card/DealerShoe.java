package academy.mindswap.Card;

import java.util.ArrayList;

public class DealerShoe {

	private final ArrayList<Deck> shoe = new ArrayList<>();

	private final int numberOfDecks = 6;

	public ArrayList<Deck> populatedShoe() {
		for (int i = 0; i < numberOfDecks; i++) {
			shoe.add(new Deck());
		}
		return shoe;
	}


	private int lastCard() {
		return shoe.size() - 1;
	}
}
