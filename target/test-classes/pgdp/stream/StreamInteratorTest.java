package pgdp.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.OptionalLong;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("8% | StreamInterator")
@W10H02
public class StreamInteratorTest {

	@DisplayName("2% | StreamInterator Interface")
	@HiddenTest
	void test_StreamInterator_definition() {
		StreamIterator<String> x = new StreamIterator<>() {

			@Override
			public StreamElement<String> next() {
				return null;
			}

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public OptionalLong getSize() {
				return OptionalLong.of(0);
			}
		};
		assertFalse(x.hasNext());
		assertNull(x.next());
	}

	@DisplayName("2% | StreamInterator of(Collection)")
	@HiddenTest
	void test_StreamInterator_Collection() {
		String sample = ".of(List.of(\"\"))";
		StreamIterator<String> x = StreamIterator.of(List.of(""));
		assertEquals(OptionalLong.of(1), x.getSize(), sample + ", Größe muss 1 sein");
		assertTrue(x.hasNext(), sample + ", hasNext() am Anfang");
		assertEquals("", x.next().getElement(), sample + ", erstes next()");
		assertFalse(x.hasNext(), sample + ", hasNext() nach 1 x next()");
		assertEquals(OptionalLong.of(1), x.getSize(), sample + ", Größe muss immernoch 1 sein");

	}

	@DisplayName("2% | StreamInterator of(Stream)")
	@HiddenTest
	void test_StreamInterator_Stream() {
		String sample = ".of(new Random().ints().boxed())";
		StreamIterator<Integer> x = StreamIterator.of(new Random().ints().boxed());
		assertEquals(OptionalLong.empty(), x.getSize(), sample + ", Größe muss unbestimmt sein");
		assertTrue(x.hasNext(), sample + ", hasNext() am Anfang");
		x.next();
		assertTrue(x.hasNext(), sample + ", hasNext() nach 1 x next()");
		assertEquals(OptionalLong.empty(), x.getSize(), sample + ", Größe muss immernoch unbestimmt sein");
	}

	@DisplayName("2% | StreamInterator of(T[])")
	@HiddenTest
	void test_StreamInterator_array() {
		String sample = ".of(new Double[] {0.5, 1.0})";
		StreamIterator<Double> x = StreamIterator.of(new Double[] { 0.5, 1.0 });
		assertEquals(OptionalLong.of(2), x.getSize(), sample + ", Größe muss 2 sein");
		assertTrue(x.hasNext(), sample + ", hasNext() am Anfang");
		assertEquals(Double.valueOf(0.5), x.next().getElement(), sample + ", erstes next()");
		assertTrue(x.hasNext(), sample + ", hasNext() nach 1 x next()");
		assertEquals(Double.valueOf(1.0), x.next().getElement(), sample + ", zweites next()");
		assertFalse(x.hasNext(), sample + ", hasNext() nach 2 x next()");
		assertEquals(OptionalLong.of(2), x.getSize(), sample + ", Größe muss immernoch 2 sein");
	}

}
