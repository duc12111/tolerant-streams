package pgdp.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.ext.DynamicClass;
import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("7% | StreamElement Basics")
@W10H02
public class StreamElementBasicTest {

	@DisplayName("2% | StreamElement Basic")
	@HiddenTest
	void test_StreamElement_basic() {
		boolean isLocked = Modifier.isFinal(StreamElement.class.getModifiers())
				|| Arrays.stream(StreamElement.class.getDeclaredConstructors())
						.allMatch(c -> !Modifier.isProtected(c.getModifiers()) && !Modifier.isPublic(c.getModifiers()));
		assertTrue(isLocked, "StreamElement sollte nicht von außerhalb beerbbar sein. (Teil der Unveränderlichkeit)");
		DynamicClass.toDynamic(StreamElement.class).checkForNonPrivateFields();
		DynamicClass.toDynamic(StreamElement.class).checkForNonFinalFields();
		assertEquals("ABC", StreamElement.of("ABC").getElement(), "StreamElement.of(\"ABC\").getElement()");
	}

	@DisplayName("3% | StreamElement Regulär")
	@HiddenTest
	void test_StreamElement_regular() {
		StreamElement<String> se = StreamElement.of("A");
		assertEquals("A", se.getElement(), "StreamElement.of(\"A\").getElement()");
		assertFalse(se.hasExceptions(), "regulär hasExceptions()");
		assertNotNull(se.getExceptions(), "regulär getExceptions()");
		assertEquals(0, se.getExceptions().size(), "regulär getExceptions().size()");
	}

	@DisplayName("2% | StreamElement Liste unveränderlich")
	@HiddenTest
	void test_StreamElement_Exceptions2() {
		StreamElement<String> se = StreamElement.of("A");
		try {
			se.getExceptions().add(new IllegalArgumentException());
		}catch (@SuppressWarnings("unused") RuntimeException e) {
			// ignore
		}
		assertEquals(0, se.getExceptions().size(), "Exception Liste darf nicht modifizierbar sein");
	}
}
