package bridgehub.cards.domain;

import static bridgehub.cards.domain.Seat.EAST;
import static bridgehub.cards.domain.Seat.NORTH;
import static bridgehub.cards.domain.Seat.WEST;
import static bridgehub.cards.domain.Suit.CLUB;
import static bridgehub.cards.domain.Suit.DIAMOND;
import static bridgehub.cards.domain.Suit.HEART;
import static bridgehub.cards.domain.Suit.SPADE;

import java.util.Arrays;

import com.google.common.base.Strings;

import bridgehub.cards.util.FArrayList;
import bridgehub.cards.util.FList;

public class Deal {
	private FList<Hand> deal = new FArrayList<Hand>();

	{
		deal.add(new Hand());
		deal.add(new Hand());
		deal.add(new Hand());
		deal.add(new Hand());
	}

	private void sort() {
		for (Hand h : deal) {
			h.sort();
		}
	}

	public String toString() {
		return deal.get(0) + " " + deal.get(1) + " " + deal.get(2) + " " + deal.get(3);
	}

	public String diagram() {
		return diagram(Vuln.ALL);
	}

	public String diagram(Vuln vuln) {
		StringBuffer sb = new StringBuffer("");
		if (Arrays.asList(Vuln.ALL, Vuln.NS).contains(vuln)) {
			sb.append("       " + hand(NORTH).suitStr1(SPADE) + "\n");
			sb.append("       " + hand(NORTH).suitStr1(HEART) + "\n");
			sb.append("       " + hand(NORTH).suitStr1(DIAMOND) + "\n");
			sb.append("       " + hand(NORTH).suitStr1(CLUB) + "\n");
		}

		if (Arrays.asList(Vuln.ALL, Vuln.EW).contains(vuln)) {
			sb.append(Strings.padEnd(hand(WEST).suitStr1(SPADE), 14, ' ')
					+ Strings.padEnd(hand(EAST).suitStr1(SPADE), 14, ' ') + "\n");
			sb.append(Strings.padEnd(hand(WEST).suitStr1(HEART), 14, ' ')
					+ Strings.padEnd(hand(EAST).suitStr1(HEART), 14, ' ') + "\n");
			sb.append(Strings.padEnd(hand(WEST).suitStr1(DIAMOND), 14, ' ')
					+ Strings.padEnd(hand(EAST).suitStr1(DIAMOND), 14, ' ') + "\n");
			sb.append(Strings.padEnd(hand(WEST).suitStr1(CLUB), 14, ' ')
					+ Strings.padEnd(hand(EAST).suitStr1(CLUB), 14, ' ') + "\n");
		}

		if (Arrays.asList(Vuln.ALL, Vuln.NS).contains(vuln)) {
			sb.append("       " + hand(Seat.SOUTH).suitStr1(SPADE) + "\n");
			sb.append("       " + hand(Seat.SOUTH).suitStr1(HEART) + "\n");
			sb.append("       " + hand(Seat.SOUTH).suitStr1(DIAMOND) + "\n");
			sb.append("       " + hand(Seat.SOUTH).suitStr1(CLUB) + "\n");
		}

		return sb.toString();
	}

	public Deal deal(Deck deck) {
		for (int i = 0; i < 52; i++) {
			deal.get(i % 4).dealCard(deck.card(i));
		}
		sort();
		return this;
	}

	public Hand hand(int i) {
		return deal.get(i);
	}

	public Hand hand(Seat seat) {
		return deal.get(seat.toInt());
	}

	public void deal(String d) {
		String[] hands = d.split(" ");
		for (int seat = 0; seat <= 3; seat++) {
			deal.get(seat).dealHand(hands[seat]);
		}
	}

	public void rotate() {
		Hand h = deal.get(3);
		deal.remove(3);
		deal.add(0, h);
	}

	public void rotate(int turns) {
		while (turns < 0) {
			turns += 4;
		}
		turns = turns % 4;
		for (int i = 0; i < turns; i++) {
			rotate();
		}
	}
}
