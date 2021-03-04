package pgdp.stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("4% | StreamOperation")
@W10H02
public class StreamOperationTest {

	@DisplayName("4% | StreamOperation")
	@HiddenTest
	void test_StreamOperation() {
		StreamOperation<Integer> x = new StreamOperation<>() {

			@Override
			public void start(StreamCharacteristics upstreamCharacteristics) {
				// nothing
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
		assertFalse(x.needsMoreElements());
	}
}
