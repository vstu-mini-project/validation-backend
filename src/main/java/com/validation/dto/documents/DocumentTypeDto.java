package com.validation.dto.documents;

import com.validation.model.documents.DocumentType;

public class DocumentTypeDto {
    Long id;
    String typeName;

    public static DocumentType toDocumentType(DocumentTypeDto documentTypeDto) {
        var documentType = new DocumentType();
        documentType.setId(documentTypeDto.getId());
        documentType.setTypeName(documentTypeDto.getTypeName());
        return  documentType;
    }

    public static DocumentTypeDto fromDocumentType(DocumentType documentType) {
        var documentTypeDto = new DocumentTypeDto();
        documentTypeDto.setId(documentType.getId());
        documentTypeDto.setTypeName(documentType.getTypeName());
        return  documentTypeDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
