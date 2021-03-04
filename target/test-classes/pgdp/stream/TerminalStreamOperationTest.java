package pgdp.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("4% | TerminalStreamOperation")
@W10H02
public class TerminalStreamOperationTest {

	@DisplayName("4% | TerminalStreamOperation")
	@HiddenTest
	void test_TerminalStreamOperation() throws Exception {
		TerminalStreamOperation<Integer, Boolean> x = new TerminalStreamOperation<>() {

			@Override
			public void start(StreamCharacteristics upstreamCharacteristics) {
				// nothing
			}

			@Override
			public Boolean get() {
				return true;
			}

			@Override
			public boolean needsMoreElements() {
				return false;
			}

			@Override
			public void acceptElement(StreamElement<Integer> element) {
				// nothing
			}

			@Override
			public void finish() {
				// nothing
			}
		};
		Supplier<Boolean> xA = x;
		assertTrue(xA.get());
		StreamOperation<Integer> xB = x;
		assertFalse(xB.needsMoreElements());
	}
}