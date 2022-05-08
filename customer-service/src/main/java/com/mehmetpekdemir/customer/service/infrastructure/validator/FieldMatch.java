package com.mehmetpekdemir.customer.service.infrastructure.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 *
 * <p>
 * Example, compare 1 pair of fields:
 * </p>
 *
 * <h5>
 *
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The
 * password fields must match")</h5>
 * <p>
 * Example, compare more than 1 pair of fields:
 * </p>
 * <h5>@FieldMatch.List({
 * @FieldMatch(first = "password", second = "confirmPassword", message = "The
 * password fields must match"),
 * @FieldMatch(first = "email", second = "confirmEmail", message = "The email
 * fields must match")})</h5>
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {

    /**
     * I can also create a list of field matching annotations.
     * <p>
     * This way we can validate field matching constraints multiple times.
     */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }

    String message() default "{default.field.match.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();
}