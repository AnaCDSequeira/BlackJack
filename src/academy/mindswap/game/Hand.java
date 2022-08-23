package academy.mindswap.game;

import java.util.ArrayList;

public class Hand {

	private static final int INITIAL_SCORE = 0;

	private static final int BLACKJACK_SCORE = 21;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card: cards) {
			builder.append(card);
			builder.append("\n");
		}
		return builder.toString();
	}
}
