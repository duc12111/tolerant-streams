package pgdp.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("17% | Streams Edge Cases")
@W10H02
public class StreamEdgeCasesTest {

	@DisplayName("7% | Test linked error")
	@HiddenTest
	void test_Stream_edge_linked() {
		Stream<Integer> of = Stream.of(1);
		of.map(i -> i);
		assertThrows(RuntimeException.class, () -> of.count());
	}

	@DisplayName("2% | Test distinct()")
	@HiddenTest
	void test_Stream_edge_distinct() {
		var a = new ArrayList<>();
		var b = new ArrayList<>();
		b.add(-30);
		var res = Stream.of(List.of(a, b)).distinct().map(list -> {
			list.add(-30);
			return list;
		}).toCollection(ArrayList::new);
		assertThat(res).containsExactly(a, b);
	}

	@DisplayName("5% | Test infinite()")
	@HiddenTest
	void test_Stream_edge_infinite() {
		Stream.of(new Random().ints().boxed()).filter(i -> i % 2 == 0).findFirst();
	}

	@DisplayName("3% | Test distinct()")
	@HiddenTest
	void test_Stream_edge_distinctCharacteristic() {
		AtomicBoolean failOnEquals = new AtomicBoolean(false);
		final class X {
			private final String s;

			public X(String s) {
				this.s = Objects.requireNonNull(s);
			}

			@Override
			public int hashCode() {
				return 0;
			}

			@Override
			public boolean equals(Object obj) {
				if (failOnEquals.get())
					fail("isDistinct (von einem Set-Stream) wird nicht zum Überspringen von distinct() nach filter genutzt");
				return super.equals(obj);
			}

			@Override
			public String toString() {
				return s;
			}
		}
		X a = new X("A");
		X b = new X("B");

		var res = Stream.of(Set.of(a, b)).filter(x -> {
			failOnEquals.set(true);
			return true;
		}).distinct().toCollection(ArrayList::new);
		failOnEquals.set(false);
		assertThat(res).containsExactlyInAnyOrder(a, b);
	}
}
