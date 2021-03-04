package pgdp.stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.ext.DynamicClass;
import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("8% | Eigene Exceptions")
@W10H02
public class ExceptionTest {

	@DisplayName("4% | ErrorsAtTerminalOperationException")
	@HiddenTest
	void test_ErrorsAtTerminalOperationException() {
		var clazz = DynamicClass.toDynamic("pgdp.stream.ErrorsAtTerminalOperationException");
		assertTrue(RuntimeException.class.isAssignableFrom(clazz.getC()),
				"ErrorsAtTerminalOperationException soll Unterklasse von RuntimeException sein");
	}

	@DisplayName("4% | CheckedStreamException")
	@HiddenTest
	void test_CheckedStreamException() {
		var clazz = DynamicClass.toDynamic("pgdp.stream.CheckedStreamException");
		assertTrue(RuntimeException.class.isAssignableFrom(clazz.getC()),
				"CheckedStreamException soll Unterklasse von RuntimeException sein");
	}
}
