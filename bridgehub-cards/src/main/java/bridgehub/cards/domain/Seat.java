package bridgehub.cards.domain;

import java.util.Arrays;

public enum Seat {
	NORTH, EAST, SOUTH, WEST;

	public static Seat of(int seat) {
		return Seat.values()[seat];
	}

	public static Seat of(String seat) {
		switch (seat) {
		case "N":
		case "NORTH":
			return NORTH;
		case "E":
		case "EAST":
			return EAST;
		case "S":
		case "SOUTH":
			return SOUTH;
		case "W":
		case "WEST":
			return WEST;
		}
		throw new RuntimeException("Invalid seat: " + seat);
	}

	public int toInt() {
		return Arrays.asList(Seat.values()).indexOf(this);
	}

	public Seat nextSeat() {
		return Seat.of((this.ordinal() + 1) % 4);
	}
}
