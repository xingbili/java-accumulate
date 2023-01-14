package msghandler.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Mapped {
	public static final String NULL = "";
	/**
 	 * The criteria by which the {@link HandlerDispatcher}
 	 * should use to route to the right handler.
	 * @return
	 */
	String value();
}