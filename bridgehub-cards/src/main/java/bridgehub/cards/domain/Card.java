package bridgehub.cards.domain;

import org.apache.commons.lang3.Validate;

public class Card implements Comparable<Card> {
	private Suit suit;
	private int rank;

	public Card(Suit suit, int rank) {
		Validate.notNull(suit);
		Validate.inclusiveBetween(2, 14, rank);
		this.suit = suit;
		this.rank = rank;
	}

	public Card(String suit, String rank) {
		this(Suit.of(suit), Rank.toInt(rank));
	}

	public Card(int suit, int rank) {
		this(Suit.of(suit), rank);
	}

	public Card(String card) {
		this(Suit.of(card.substring(0, 1)), Rank.toInt(card.substring(1, 2)));
		Validate.isTrue(2 == card.length());
	}

	public int rank() {
		return rank;
	}

	public Suit suit() {
		return suit;
	}

	public String symbol() {
		return suit.symbol() + Rank.symbol(rank);
	}

	public int compareTo(Card o) {
		if (o.suit != this.suit) {
			return Integer.compare(this.suit.toInt(), o.suit.toInt());
		}
		return Integer.compare(o.rank, this.rank);
	}

	public int hcp() {
		if (this.rank <= 10) {
			return 0;
		} else {
			return this.rank - 10;
		}
	}

	@Override
	public String toString() {
		return suit.symbol() + Rank.symbol(rank);
	}
}
