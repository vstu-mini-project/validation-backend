package com.validation.dto;

import com.validation.model.Document;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentDto {

    Long id;
    VerificationDto verification;
    UserDto user;

    public static DocumentDto fromDocument(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .verification(VerificationDto.fromVerification(document.getVerification()))
                .user(UserDto.fromUser(document.getUser()))
                .build();
    }

    public static Document toDocument(DocumentDto dto) {
        return Document.builder()
                .id(dto.getId())
                .verification(VerificationDto.toVerification(dto.getVerification()))
                .user(UserDto.toUser(dto.getUser()))
                .build();
    }
}
