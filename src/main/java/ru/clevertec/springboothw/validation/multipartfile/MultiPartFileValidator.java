package ru.clevertec.springboothw.validation.multipartfile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiPartFileValidator implements ConstraintValidator<ValidMediaType, MultipartFile> {
    private Pattern pattern;

    @Override
    public void initialize(ValidMediaType constraintAnnotation) {
        pattern = Pattern.compile(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .map(MultipartFile::getContentType)
                .map(pattern::matcher)
                .map(Matcher::matches)
                .orElse(true);
    }
}
