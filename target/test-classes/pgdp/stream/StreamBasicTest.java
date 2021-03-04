package pgdp.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.PublicTest;

@DisplayName("Nur öffentliche Tests")
@W10H02
public class StreamBasicTest {

	@PublicTest
	void test_Stream_public_empty_count() {
		assertEquals(0, Stream.of().count(), "Leerer Stream sollte keine Elemente enthalten");
	}

	@PublicTest
	void test_Stream_public_empty_AL() {
		assertEquals(new ArrayList<>(), Stream.of().toCollection(ArrayList::new),
				"Leerer Stream sollte leere ArrayList liefern");
	}

	@PublicTest
	void test_Stream_public_some_count() {
		assertEquals(3, Stream.of(1, 2, 3).count(), "[1, 2, 3] sollte 3 Elemente enthalten");
	}

	@PublicTest
	void test_Stream_public_some_AL() {
		var act = Stream.of(1, 2, 3).toCollection(ArrayList::new);
		assertThat(act).as("[1, 2, 3] sollte ArrayList mit [1, 2, 3] liefern").containsExactly(1, 2, 3);
	}

	@PublicTest
	void test_Stream_public_filter_count() {
		assertEquals(1, Stream.of(1, 2, 3).filter(i -> i % 2 == 0).count(),
				"[1, 2, 3] gefiltert nach geraden Zahlen sollte 1 Element enthalten");
	}

	@PublicTest
	void test_Stream_public_map_filter_find() {
		assertEquals(Optional.of(4), Stream.of(1, 2, 3).map(i -> i * i).filter(i -> i % 2 == 0).findFirst(),
				"[1, 2, 3] quadriert und gefiltert nach geraden Zahlen sollte 4 als erstes Element liefern");
	}

	@PublicTest
	void test_Stream_public_map_ex_count() {
		assertEquals(3, Stream.of(1, 2, 3).map(i -> {
			if (i % 2 == 0)
				throw new IllegalArgumentException();
			return i;
		}).count(), "[1, 2, 3] fehlerhaft gemapped sollte trotzdem sicher 3 Elemente haben");
	}

	@PublicTest
	void test_Stream_public_complex_1() {
		var act = Stream.of(1, 2, 3, 4, 5).map(i -> {
			if (i % 2 == 0)
				throw new IllegalArgumentException();
			return i;
		}).filter(i -> i > 1).onErrorMap(list -> 42).toCollection(ArrayList::new);

		assertThat(act).as("Erste komplexe Operation").containsExactly(42, 3, 42, 5);
	}


	@PublicTest
	void test_Stream_public_complex_3() {
		var act = Stream.of(1, 2, 3, 4, 5, 6).map(i -> i / (i - 1)).distinct().onErrorFilter()
				.toCollection(ArrayList::new);

		assertThat(act).as("Erste komplexe Operation").containsExactly(2, 1);
	}
}
