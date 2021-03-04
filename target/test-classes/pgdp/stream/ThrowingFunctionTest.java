package pgdp.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("4% | ThrowingFunction")
@W10H02
public class ThrowingFunctionTest {

	@DisplayName("4% | ThrowingFunction")
	@HiddenTest
	void test_ThrowingFunction() throws Exception {
		ThrowingFunction<Integer, String> x = i -> {
			if (i > 0)
				throw new Exception("a");
			return "x";
		};
		assertEquals("x", x.apply(-1));
	}
}
