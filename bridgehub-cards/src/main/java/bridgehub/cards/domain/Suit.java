package bridgehub.cards.domain;

import java.util.Arrays;
import java.util.List;

public enum Suit {
	CLUB, DIAMOND, HEART, SPADE;

	public static List<String> SYMBOL = Arrays.asList("C", "D", "H", "S");

	public static Suit of(int suit) {
		return Suit.values()[suit];
	}

	public static Suit of(String s) {
		switch (s) {
		case "C":
		case "CLUB":
		case "CLUBS":
			return Suit.CLUB;
		case "D":
		case "DIAMOND":
		case "DIAMONDS":
			return Suit.DIAMOND;
		case "H":
		case "HEART":
		case "HEARTS":
			return Suit.HEART;
		case "S":
		case "SPADE":
		case "SPADES":
			return Suit.SPADE;
		default:
			throw new RuntimeException("Invalid suit: " + s);
		}
	}

	public int toInt() {
		return this.ordinal();
	}

	public boolean isMajor() {
		return Arrays.asList(SPADE, HEART).contains(this);
	}

	public boolean isMinor() {
		return Arrays.asList(DIAMOND, CLUB).contains(this);
	}

	public boolean isSpades() {
		return SPADE.equals(this);
	}

	public boolean isHearts() {
		return HEART.equals(this);
	}

	public boolean isDiamonds() {
		return DIAMOND.equals(this);
	}

	public boolean isClubs() {
		return CLUB.equals(this);
	}

	public boolean isHigherThan(Suit s) {
		return this.ordinal() > s.ordinal();
	}

	public boolean isLowerThan(Suit s) {
		return s.isHigherThan(this);
	}

	public Suit otherMajor() {
		switch (this) {
		case SPADE:
			return HEART;
		case HEART:
			return SPADE;
		default:
			throw new RuntimeException("Invalid operation");
		}
	}

	public Suit otherMinor() {
		switch (this) {
		case DIAMOND:
			return CLUB;
		case CLUB:
			return DIAMOND;
		default:
			throw new RuntimeException("Invalid operation");
		}
	}

	public String symbol() {
		return toString().substring(0, 1);
	}

	@Override
	public String toString() {
		return name().substring(0, 1);
	}
}
