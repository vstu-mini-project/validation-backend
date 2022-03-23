package com.validation.service;

import com.validation.model.Document;
import com.validation.model.User;
import com.validation.model.documents.DocumentType;
import com.validation.repository.DocumentRepository;
import com.validation.repository.documents.DocumentTypeRepository;
import com.validation.repository.documents.PassportRepository;
import com.validation.repository.documents.StudentCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PassportRepository passportRepository;
    private final StudentCertificateRepository studentCertificateRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentTypeRepository documentTypeRepository, PassportRepository passportRepository, StudentCertificateRepository studentCertificateRepository) {
        this.documentRepository = documentRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.passportRepository = passportRepository;
        this.studentCertificateRepository = studentCertificateRepository;
    }

    public Document addDocument(User user, DocumentType documentType) {
        Document newDocument = new Document();
        newDocument.setUser(user);

        String type = documentType.getTypeName();

        switch (type) {
            case "Паспорт":

                break;
            default:
                break;
        }

        return documentRepository.save(newDocument);
    }


}
