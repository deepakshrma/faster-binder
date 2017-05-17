package org.fasterbinder.xdeepakv.annotations;

/**
 * Created by dvishwakarma on 5/17/2017.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FieldBinder {
    String key();
}
