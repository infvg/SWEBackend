package vg.inf.util;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Utils {
	public static void loop(int times, IntConsumer action) {
		IntStream.iterate(1, i -> i < times, i -> i + 1).forEach(action);
	}
}
