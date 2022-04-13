package com.validation.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionDto {

    String error;
    String errorDescription;

    public ExceptionDto(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }
}
