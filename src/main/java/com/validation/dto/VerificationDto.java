package com.validation.dto;

import com.validation.model.Verification;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationDto {

    Long id;
    LocalDate expirationDate;
    String url;

    public static Verification toVerification(VerificationDto verificationDto) {
        return Verification.builder()
                .id(verificationDto.getId())
                .expirationDate(verificationDto.getExpirationDate())
                .url(verificationDto.getUrl())
                .build();
    }

    public static VerificationDto fromVerification(Verification verification) {
        return VerificationDto.builder()
                .id(verification.getId())
                .expirationDate(verification.getExpirationDate())
                .url(verification.getUrl())
                .build();
    }
}
