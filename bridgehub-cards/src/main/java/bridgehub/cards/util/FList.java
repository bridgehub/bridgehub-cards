package bridgehub.cards.util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface FList<T> extends List<T> {

	public static <A, B> List<B> map(List<A> list, Function<A, B> f) {
		return list.stream().map(f).collect(Collectors.toList());
	}

	public static <A> List<A> filter(List<A> list, Predicate<A> f) {
		return list.stream().filter(f).collect(Collectors.toList());
	}

	default <R> FList<R> map(Function<T, R> f) {
		FList<R> result = new FArrayList<R>();
		for (T item : this) {
			result.add(f.apply(item));
		}
		return result;
	};

	default <R> R reduce(R aggr, Function<T, R> f, BiFunction<R, R, R> bif) {
		for (T item : this) {
			aggr = bif.apply(f.apply(item), aggr);
		}
		return aggr;
	};

	default FList<T> filter(Predicate<T> p) {
		FList<T> result = new FArrayList<T>();
		for (T item : this) {
			if (p.test(item)) {
				result.add(item);
			}
		}
		return result;
	};
}
