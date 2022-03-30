package com.validation.service;

import com.validation.model.Document;
import com.validation.model.User;
import com.validation.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document add(User user, Document document) {
        document.setUser(user);
        List<Document> documents = user.getDocuments();
        documents.add(document);
        user.setDocuments(documents);
        return documentRepository.save(document);
    }


}
