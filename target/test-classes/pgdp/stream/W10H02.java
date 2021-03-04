package pgdp.stream;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.tum.in.test.api.ActivateHiddenBefore;
import de.tum.in.test.api.AllowThreads;
import de.tum.in.test.api.BlacklistPath;
import de.tum.in.test.api.Deadline;
import de.tum.in.test.api.MirrorOutput;
import de.tum.in.test.api.MirrorOutput.MirrorOutputPolicy;
import de.tum.in.test.api.PathType;
import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistPath;

/**
 * This just combines the other annotations for consistency to be used in all
 * test classes. If you happen to be a tutor, you can change whatever you like
 * for testing, like commenting in a more general @WhitelistPath.
 *
 */
@MirrorOutput(MirrorOutputPolicy.ENABLED)
@StrictTimeout(10)
@WhitelistPath("..")
@WhitelistPath("target/")
@BlacklistPath(value = "**Test.{java,class}", type = PathType.GLOB)
@Deadline("2020-01-13 05:30")
@ActivateHiddenBefore("2020-01-05 05:30")
@AllowThreads
@Target(TYPE)
@Retention(RUNTIME)
public @interface W10H02 {
	// marker only
}
