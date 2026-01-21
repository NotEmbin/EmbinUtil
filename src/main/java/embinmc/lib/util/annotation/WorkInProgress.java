package embinmc.lib.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.MODULE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkInProgress {
}
