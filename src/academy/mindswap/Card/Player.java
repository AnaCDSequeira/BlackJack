package academy.mindswap.Card;

import java.util.ArrayList;

public abstract class Player { //dealer e client ou interface funcional?

	private ArrayList<Card> hand;

	private String name;

	private int score;
	private int amountToBet;

	private boolean willPlay = false;

	public Player(String name, int amountToBet) {
		this.name = name;
		this.amountToBet = amountToBet;
		this.hand = new ArrayList<>();
	}

	public void dealCards(Card card) {
		hand.add(card);
		updateScore();
	}

	private void updateScore() {
		for (Card card : hand) {
			score += card.getValue().getValue();
		}
	}


}
