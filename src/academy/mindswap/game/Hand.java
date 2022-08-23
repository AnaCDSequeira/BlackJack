package academy.mindswap.game;

import java.util.ArrayList;

public class Hand {

	private static final int INITIAL_SCORE = 0;
	private static final int BLACKJACK_SCORE = 21;
	public static final int FIRST_CARD_POSITION = 0;

	private final ArrayList<Card> cards;
	private int score;

	public Hand() {
		cards = new ArrayList<>();
		score = INITIAL_SCORE;
	}

	public int getScore() {
		return score;
	}

	public void addCard(Card card) {
		cards.add(card);
		updateScore(Integer.parseInt(card.getValue()));
	}

	private void updateScore(int value) {
		score += value;
	}

	public void clear() {
		cards.clear();
		score = INITIAL_SCORE;
	}

	public boolean canPlay() {
		return score < BLACKJACK_SCORE;
	}

	public boolean hasBlackJack() {
		return score == BLACKJACK_SCORE;
	}

	public boolean hasBusted() {
		return score > BLACKJACK_SCORE;
	}

	public Card showFirstCard(){
		return cards.get(FIRST_CARD_POSITION);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("Your cards are:\n");
		for (int i = 0; i < cards.size(); i++) {
			builder.append(i + 1);
			builder.append(". ");
			builder.append(cards.get(i));
			builder.append(" - ");
			builder.append(cards.get(i).getValue());
			builder.append(" points");
			builder.append("\n");
		}
		return builder.toString();
	}
}
