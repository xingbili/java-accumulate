package msghandler.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
/**
 * Parent is the annotation to specify 
 * the parent handler of a certain sub-message.
 * @author smo1szh
 *
 */
public @interface Parent {
	/**
	 * Parent's field name.
	 * @return
	 */
	String value();
}