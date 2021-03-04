package pgdp.stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;

import de.tum.in.test.api.jupiter.HiddenTest;

@DisplayName("4% | ThrowingPredicate")
@W10H02
public class ThrowingPredicateTest {

	@DisplayName("4% | ThrowingPredicate")
	@HiddenTest
	void test_ThrowingPredicate() throws Exception {
		ThrowingPredicate<Integer> x = i -> {
			if (i > 0)
				throw new Exception("a");
			return false;
		};
		assertFalse(x.test(-1));
	}
}
