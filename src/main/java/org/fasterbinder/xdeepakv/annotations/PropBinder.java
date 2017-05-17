package org.fasterbinder.xdeepakv.annotations;

/**
 * Created by dvishwakarma on 5/17/2017.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropBinder {
    String path();
    PropBinderType type() default PropBinderType.JSON;
    enum PropBinderType{
        JSON, YML
    }
}

