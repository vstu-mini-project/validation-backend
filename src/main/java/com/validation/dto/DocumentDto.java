package com.validation.dto;

import com.validation.model.Document;

public class DocumentDto {

    private Long id;
    private VerificationDto verification;
    private UserDto user;

    public static DocumentDto fromDocument(Document document) {
        DocumentDto dto = new DocumentDto();
        dto.setId(document.getId());
        dto.setUser(UserDto.fromUser(document.getUser()));
        dto.setVerification(VerificationDto.fromVerification(document.getVerification()));
        return dto;
    }

    public static Document toDocument(DocumentDto dto) {
        Document document = new Document();
        document.setId(dto.getId());
        document.setUser(dto.getUser().toUser());
        document.setVerification(VerificationDto.toVerification(dto.getVerification()));
        return document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VerificationDto getVerification() {
        return verification;
    }

    public void setVerification(VerificationDto verification) {
        this.verification = verification;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
