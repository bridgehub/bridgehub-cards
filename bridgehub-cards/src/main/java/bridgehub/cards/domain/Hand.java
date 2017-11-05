package bridgehub.cards.domain;

import static bridgehub.cards.domain.Suit.CLUB;
import static bridgehub.cards.domain.Suit.DIAMOND;
import static bridgehub.cards.domain.Suit.HEART;
import static bridgehub.cards.domain.Suit.SPADE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bridgehub.cards.util.FArrayList;
import bridgehub.cards.util.FList;

public class Hand {
	public Map<Suit, FList<Card>> hand = new TreeMap<>();

	public Hand() {
		init();
	}

	private void init() {
		for (int s = 0; s < 4; s++) {
			hand.put(CLUB, new FArrayList<Card>());
			hand.put(DIAMOND, new FArrayList<Card>());
			hand.put(HEART, new FArrayList<Card>());
			hand.put(SPADE, new FArrayList<Card>());
		}
	}

	public void dealCard(Card card) {
		hand.get(card.suit()).add(card);
	}

	public void dealCard(String suitRank) {
		String suit = suitRank.substring(0, 1);
		String rank = suitRank.substring(1, 2);
		dealCard(new Card(suit, rank));

	}

	public void dealCards(String... cards) {
		init();
		for (String card : cards) {
			dealCard(card);
		}
		sort();
	}

	public void sort() {
		for (List<Card> suit : hand.values()) {
			Collections.sort(suit);
		}
	}

	public String toString() {
		return strSuit(hand.get(SPADE)) + "." + strSuit(hand.get(HEART)) + "." + strSuit(hand.get(DIAMOND)) + "."
				+ strSuit(hand.get(CLUB));
	}

	private String strSuit(List<Card> list) {
		String result = "";
		for (Card c : list) {
			result += Rank.symbol(c.rank());
		}
		return result;
	};

	public int tp() {
		// TODO implement totalPoints
		return hcp();
	}

	public int hcp() {
		int result = 0;
		for (FList<Card> suit : hand.values()) {
			result += hcp(suit);
		}
		return result;
	}

	public static int hcp(FList<Card> suit, int offset) {
		int result = 0;
		for (Card card : suit) {
			int p = card.hcp();
			if (p >= 1) {
				p += offset;
			}
			result += p;
		}
		return result;
	}

	public static int hcp(FList<Card> suit) {
		return hcp(suit, 0);
	}

	public FList<Integer> distribution() {
		FList<Integer> result = new FArrayList<Integer>();
		for (Suit suit : Suit.values()) {
			result.add(hand.get(suit).size());
		}
		return result;
	}

	public String distrSpecific() {
		FList<Integer> result = distribution();
		Collections.reverse(result);
		return String.join("-", result.map(e -> "" + e));
	}

	public String distrPattern() {
		FList<Integer> result = distribution();
		Collections.sort(result);
		Collections.reverse(result);
		return String.join("-", result.map(e -> "" + e));
	}

	public Hand dealHand(String hand) {
		boolean fullHand = true;
		return dealHand(hand, fullHand);
	}

	public Hand dealHand(String hand, boolean fullHand) {
		this.init();
		int cnt = 0;
		List<String> suits = Arrays.asList(hand.split("\\."));
		for (Suit suit : Suit.values()) {
			if (suits.size() > 3 - suit.toInt()) {
				String suitData = suits.get(3 - suit.toInt());
				List<String> cards = Arrays.asList(suitData.split(""));
				for (String card : cards) {
					if (!"".equals(card)) {
						this.dealCard(new Card(suit, Rank.toInt(card)));
						cnt++;
					}
				}
			}
		}
		if (cnt > 13) {
			throw new RuntimeException("Number of cards must not be more than 13");
		}
		if (fullHand && (cnt < 13)) {
			throw new RuntimeException("Number of cards must be 13");
		}
		return this;
	}

	public FList<Card> suit(Suit suit) {
		return hand.get(suit);
	}

	public FList<Card> spades() {
		return hand.get(Suit.SPADE);
	}

	public FList<Card> hearts() {
		return hand.get(Suit.HEART);
	}

	public FList<Card> diamonds() {
		return hand.get(Suit.DIAMOND);
	}

	public FList<Card> clubs() {
		return hand.get(Suit.CLUB);
	}

	public String suitStr(Suit suit) {
		return String.join("", hand.get(suit).map(c -> Rank.symbol(c.rank())));
	}

	public String suitStr1(Suit suit) {
		String result = suitStr(suit);
		if ("".equals(result)) {
			return "-";
		}
		return result;
	}

	final List<String> BALANCED_PATTERN = Arrays.asList("4-3-3-3", "4-4-3-2", "5-3-3-2");
	final List<String> BALANCED_PATTERN_4CARD = Arrays.asList("4-3-3-3", "4-4-3-2");
	final List<String> BALANCED_PATTERN_5CARD = Arrays.asList("5-3-3-2");
	final List<String> SEMI_BALANCED_PATTERN = Arrays.asList("5-4-2-2", "6-3-2-2");

	public boolean isBalanced() {
		return BALANCED_PATTERN.contains(distrPattern());
	}

	public boolean isBalanced4Card() {
		return BALANCED_PATTERN_4CARD.contains(distrPattern());
	}

	public boolean isBalanced5Card() {
		return BALANCED_PATTERN_5CARD.contains(distrPattern());
	}

	public boolean isSemibalanced() {
		return SEMI_BALANCED_PATTERN.contains(distrPattern());
	}

	public boolean isUnbalanced() {
		return (!isBalanced()) && (!isUnbalanced());
	}

	public int ltc() {
		// TODO [missing-impl] Hand.ltc()
		return 0;
	}
}
