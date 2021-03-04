package pgdp.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("13% | Streams mit Checked Exceptions")
@W10H02
public class StreamAdvHiddenTest {

	@DisplayName("7% | Streams mit Checked Exceptions")
	@HiddenTest
	void test_Stream_hidden_complex_2() {
		var act = Stream.of(1, 4, -45, 3, 7, 6).mapChecked(i -> {
			if (i % 3 == 0)
				throw new IOException("x:" + i);
			return i;
		}).filter(i -> i != 5).map(i -> i == 4 ? null : i).map(Object::toString)
				.onErrorMap(list -> list.get(0).getMessage()).onErrorFilter().toCollection(ArrayList::new);

		assertThat(act).as("Erste komplexe Operation").containsExactly("1", null, "x:-45", "x:3", "7", "x:6");
	}

	@SuppressWarnings("unchecked")
	@DisplayName("6% | Streams mit CheckedStreamException")
	@HiddenTest
	void test_Stream_hidden_checked() {
		try {
			assertThrows((Class<? extends Exception>) Class.forName("pgdp.stream.CheckedStreamException"),
					() -> Stream.of(1, 2, 99, 5, 6).mapChecked(i -> {
						if (i > 100)
							throw new IOException("x:" + i);
						return i;
					}).filter(Objects::nonNull).reduce(Integer::sum), "Muss fehlschlagen, wegen Checked Stream");
		} catch (ClassCastException | ClassNotFoundException e) {
			fail("CheckedStreamException ist inkorrekt: " + e);
		}
	}
}
