package ru.clevertec.springboothw.validation.multipartfile;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultiPartFileValidator.class)
public @interface ValidMediaType {
    String value() default "image/png|image/jpg|image/jpeg";

    String message() default "Invalid type format: ${validatedValue.originalFilename}, use only {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
