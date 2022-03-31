package com.validation.dto;

import com.validation.model.Verification;

import java.time.LocalDate;

public class VerificationDto {
    private Long id;
    private LocalDate expirationDate;
    private String url;

    public static Verification toVerification(VerificationDto verificationDto) {
        var verification = new Verification();
        verification.setId(verificationDto.getId());
        verification.setExpirationDate(verificationDto.getExpirationDate());
        verification.setUrl(verificationDto.getUrl());
        return verification;
    }

    public static VerificationDto fromVerification(Verification verification) {
        var verificationDto = new VerificationDto();
        verificationDto.setId(verification.getId());
        verificationDto.setExpirationDate(verification.getExpirationDate());
        verificationDto.setUrl(verification.getUrl());
        return verificationDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
