package bridgehub.cards.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class Rank {
	private static List<String> RANK = //
	Arrays.asList("?", "?", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

	public static int toInt(String r) {
		int result = RANK.indexOf(r);
		Validate.isTrue(result >= 2, r);
		Validate.isTrue(result <= 14, r);
		return result;
	}

	public static String symbol(int r) {
		Validate.isTrue(r >= 2);
		Validate.isTrue(r <= 14);
		return RANK.get(r);
	}
}
