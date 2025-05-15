package org.milan.annotation;

import org.milan.validator.HobbyValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation related to hobby validation
 *
 * @author Milan Rathod
 */
@Documented
@Constraint(validatedBy = {HobbyValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby {
    String listOfHobbies() default "Music|Movie|Cricket";

    String message() default "Please provide valid hobby " +
        "Accepted hobbies are Music,Movie,Cricket";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
