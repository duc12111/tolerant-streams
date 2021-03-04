package pgdp.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import java.util.OptionalLong;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.ext.DynamicClass;
import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("5% | StreamCharacteristics")
@W10H02
public class StreamCharacteristicsTest {

	@DisplayName("3% | StreamCharacteristics Basic")
	@HiddenTest
	void test_StreamCharacteristics_basic() {
		var clazz = DynamicClass.toDynamic(StreamCharacteristics.class);
		clazz.checkForNonPrivateFields();
		clazz.checkForNonFinalFields();
		assertTrue(Modifier.isFinal(clazz.getC().getModifiers()), "StreamCharacteristics sollte final sein.");
	}

	@DisplayName("2% | StreamCharacteristics Basic")
	@HiddenTest
	void test_StreamCharacteristics_methods() {
		var sc = StreamCharacteristics.regular();
		assertEquals(OptionalLong.empty(), sc.getStreamSize(), "Normalfall: keine Größe bekannt");
		assertFalse(sc.isChecked(), "Normalfall: enthält garantiert keine checked Exceptions");
		assertFalse(sc.isDistinct(), "Normalfall: es kann Duplikate geben");
	}
}
