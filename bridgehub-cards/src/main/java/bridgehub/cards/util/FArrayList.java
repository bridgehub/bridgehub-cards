package bridgehub.cards.util;

import java.util.ArrayList;
import java.util.List;

public class FArrayList<T> extends ArrayList<T> implements List<T>, FList<T> {

	private static final long serialVersionUID = 1L;

	public FArrayList() {
		super();
	}

	public FArrayList(List<T> list) {
		super();
		this.addAll(list);
	}
}
