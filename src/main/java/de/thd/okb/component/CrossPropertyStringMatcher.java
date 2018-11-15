package de.thd.okb.component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {CrossPropertyStringMatcherValidator.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface CrossPropertyStringMatcher {
    String message() default "{de.thd.okb.crosspropertystringmatcher.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String sourceProperty();

    String targetProperty();

}
