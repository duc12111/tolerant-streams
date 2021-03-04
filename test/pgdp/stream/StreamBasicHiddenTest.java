package pgdp.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("20% | Streams")
@W10H02
public class StreamBasicHiddenTest {

	@DisplayName("2% | Leerer Stream ")
	@HiddenTest
	void test_Stream_hidden_empty_count() {
		assertEquals(0, Stream.of().count(), "Leerer Stream sollte keine Elemente enthalten");
	}

	@DisplayName("2% | Leerer Stream reduce")
	@HiddenTest
	void test_Stream_hidden_empty_reduce() {
		assertEquals(Optional.empty(), Stream.<Integer>of().reduce(Integer::sum), "Leerer Stream reduce sollte leeres Optional liefern");
	}

	@DisplayName("2% | [2, 3, 4, 5] count()")
	@HiddenTest
	void test_Stream_hidden_some_count() {
		assertEquals(4, Stream.of(2, 3, 4, 5).count(), "[2, 3, 4, 5] sollte 4 Elemente enthalten");
	}

	@DisplayName("2% | [42.0, 83.0] toCollection")
	@HiddenTest
	void test_Stream_hidden_some_AL() {
		var act = Stream.of(42.0, 83.0).toCollection(HashSet::new);
		assertThat(act).as("[42.0, 83.0] sollte ArrayList mit [42.0, 83.0] liefern").containsExactlyInAnyOrder(42.0,
				83.0);
	}

	@DisplayName("2% | [123, 5, 6, 6, 5] gerade gefiltert und count() | REFERENZ")
	@HiddenTest
	void test_Stream_hidden_filter_count() {
		assertEquals(2, Stream.of(123, 5, 6, 6, 5).filter(i -> i % 2 == 0).count(),
				"[123, 5, 6, 6, 5] gefiltert nach geraden Zahlen sollte 2 Elemente enthalten");
	}

	@DisplayName("2% | [1.0, 2.0, 3.0, 4.0] quadriert und gerade gefilter findFist() | REFERENZ")
	@HiddenTest
	void test_Stream_hidden_map_filter_find() {
		assertEquals(Optional.of(4.0),
				Stream.of(1.0, 2.0, 3.0, 4.0).map(i -> i * i).filter(i -> i % 2 == 0).findFirst(),
				"[1.0, 2.0, 3.0, 4.0] quadriert und gefiltert nach geraden Zahlen sollte 4.0 als erstes Element liefern");
	}

	@DisplayName("2% | [1, 2, 3, 4, 5, 6] fehlerhaft gemappt und reguläre gefiltert und Fehler gemappt")
	@HiddenTest
	void test_Stream_hidden_ex_count() {
		assertEquals(2, Stream.of(1, 2, 3, 4, 5, 6).map(i -> {
			if (i % 3 == 0)
				throw new IllegalArgumentException();
			return i;
		}).filter(i -> false).onErrorMap(es -> 1).count(),
				"[1, 2, 3, 4, 5, 6] fehlerhaft gemappt und reguläre gefiltert und Fehler gemappt solle bei mod 3 == 0 2 ergeben");
	}

	@DisplayName("2% | [1, 2, 5] fehlerhaft gemapped und count()")
	@HiddenTest
	void test_Stream_hidden_map_ex_count() {
		assertEquals(3, Stream.of(1, 2, 5).map(i -> {
			if (i % 1 == 0)
				throw new IllegalArgumentException();
			return i;
		}).count(), "[1, 2, 5] fehlerhaft gemapped sollte trotzdem sicher 3 Elemente haben");
	}

	@DisplayName("2% | [10, 31, 30, 41, 50] fehlerhaft gemapped, Fehler gemappt und toCollection() | REFERENZ")
	@HiddenTest
	void test_Stream_hidden_complex_1() {
		var act = Stream.of(10, 31, 30, 41, 50).map(i -> {
			if (i % 2 == 0)
				throw new IllegalArgumentException();
			return i;
		}).filter(i -> i > 30).onErrorMap(list -> 42).toCollection(ArrayList::new);

		assertThat(act).as("Erste komplexe Operation mit ").containsExactly(42, 31, 42, 41, 42);
	}

	@DisplayName("2% | [1, 2, 3, 4, 5, -1] fehlerhaft gemapped, Fehler gemappt und toCollection()")
	@HiddenTest
	void test_Stream_hidden_complex_3() {
		var act = Stream.of(1, 2, 3, 4, 5, -1).map(i -> i / (i - 1)).distinct().onErrorFilter()
				.toCollection(ArrayList::new);

		assertThat(act).as("[1, 2, 3, 4, 5, -1]").containsExactly(2, 1, 0);
	}
}
