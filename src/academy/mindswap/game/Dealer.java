package academy.mindswap.game;

/**
 * Class extending from Person that creates a player which will be the game's dealer
 * Super class has a hand which is a list of cards
 */

public class Dealer extends Person {

	private static final int MINIMUM_VALUE_TO_DRAW = 16;  // minimum value the hand must have before stop asking new cards

	public Dealer() {
		super();
	}  // calls super constructor to create a new Hand

	/**
	 * Method to get the first card of a hand (list)
	 * @return the first card of the list
	 */
	public Card firstCard() {
		return getHand().showFirstCard();
	}

	/**
	 * Method to check if dealer must play (case score got by sum of cards value in hand is less than MINIMUM_VALUE_TO_DRAW)
	 * @return true or false depending on condition above explained
	 */
	public boolean shouldDrawCards() {
		return getHand().getScore() <= MINIMUM_VALUE_TO_DRAW;
	}

	public int getScore() {
		return getHand().getScore();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
}
