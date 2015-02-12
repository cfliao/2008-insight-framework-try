package s2h.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import s2h.platform.node.PlatformTopic;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ListenTopic {
    PlatformTopic topic() default PlatformTopic.NULL;
}
