package com.validation.dto.documents;

import com.validation.model.documents.DocumentType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentTypeDto {

    Long id;
    String typeName;

    public static DocumentType toDocumentType(DocumentTypeDto documentTypeDto) {
        return DocumentType.builder()
                .id(documentTypeDto.getId())
                .typeName(documentTypeDto.getTypeName())
                .build();
    }

    public static DocumentTypeDto fromDocumentType(DocumentType documentType) {
        return DocumentTypeDto.builder()
                .id(documentType.getId())
                .typeName(documentType.getTypeName())
                .build();
    }
}
