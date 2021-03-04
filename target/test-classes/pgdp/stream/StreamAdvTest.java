package pgdp.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.PublicTest;

@DisplayName("Nur öffentliche Tests")
@W10H02
public class StreamAdvTest {

	@PublicTest
	void test_Stream_public_complex_2() {
		var act = Stream.of(1, 2, 3, 4, 5, 6).mapChecked(i -> {
			if (i % 3 == 0)
				throw new IOException("x:" + i);
			return i;
		}).filter(i -> i != 5).map(i -> i == 4 ? null : i).map(Object::toString)
				.onErrorMap(list -> list.get(0).getMessage()).toCollection(ArrayList::new);

		assertThat(act).as("Erste komplexe Operation").containsExactly("1", "2", "x:3", null, "x:6");
	}

	@PublicTest
	@SuppressWarnings("unchecked")
	void test_Stream_public_checked() {
		try {
			assertThrows((Class<? extends Exception>) Class.forName("pgdp.stream.CheckedStreamException"),
					() -> Stream.of(1, 2, 3, 4, 5, 6).mapChecked(i -> {
						if (i > 10)
							throw new IOException("x:" + i);
						return i;
					}).map(x -> x).reduce(Integer::sum), "Muss fehlschlagen, wegen Checked Stream");
		} catch (ClassCastException | ClassNotFoundException e) {
			fail("CheckedStreamException ist inkorrekt: " + e);
		}
	}
}
