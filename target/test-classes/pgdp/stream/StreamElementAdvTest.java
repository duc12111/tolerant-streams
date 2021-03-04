package pgdp.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("4% | StreamElement Advanced")
@W10H02
public class StreamElementAdvTest {

	@DisplayName("4% | StreamElement tryAdapt() und withExceptionAdded()")
	@HiddenTest
	void test_StreamElement_advanced() {
		try {
			StreamElement.of(1).tryAdapt();
			fail("StreamElement.of(1).tryAdapt() muss fehlschlagen");
		} catch (@SuppressWarnings("unused") RuntimeException e) {
			// ignore
		}
		IOException ioe = new IOException();
		StreamElement<Double> x = StreamElement.of(1).withExceptionAdded(ioe);
		StreamElement<String> y = x.tryAdapt();
		assertTrue(x.hasExceptions(), "muss nach withExceptionAdded fehlerhaft sein");
		assertTrue(y.hasExceptions(), "muss nach withExceptionAdded fehlerhaft sein");
		assertNotNull(y.getExceptions(), "fehlerhaft getExceptions()");
		assertEquals(1, y.getExceptions().size(), "fehlerhaft getExceptions().size()");
		assertEquals(ioe, y.getExceptions().get(0), "fehlerhaft enthält Exception");
	}
}
