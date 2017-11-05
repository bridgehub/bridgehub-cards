package bridgehub.cards.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> deck = new ArrayList<Card>();
	private Random rnd;
	private static long SEED_DEFAULT = 2305843009213693951L;

	public Deck() {
		this(SEED_DEFAULT);
	}

	public Deck(long seed) {
		rnd = new Random(seed);
		init();
	}

	public void shuffle() {
		for (int i = 0; i < 52; i++) {
			int j = rnd.nextInt(52);
			Card t = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, t);
		}
	}

	private void init() {
		deck.clear();
		for (int s = 0; s <= 3; s++) {
			for (int r = 2; r <= 14; r++) {
				deck.add(new Card(s, r));
			}
		}
		shuffle();
	}

	public Card card(int i) {
		return deck.get(i);
	}

	@Override
	public String toString() {
		return "Deck [deck=" + deck + "]";
	}
}
