package iit.ase.cw.platform.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface TimeStamp {

    String IGNORE_TIME = "IGNORE_TIME";

    String action() default IGNORE_TIME;
}

