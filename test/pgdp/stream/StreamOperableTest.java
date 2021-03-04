package pgdp.stream;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("2% | StreamOperable")
@W10H02
public class StreamOperableTest {

	@DisplayName("2% | StreamOperable")
	@HiddenTest
	void test_StreamOperable() {
		StreamOperable<Integer> x = new StreamOperable<>() {

			@Override
			public StreamOperation<Integer> getStreamOperation() {
				return null;
			}

		};
		assertNull(x.getStreamOperation());
	}
}
