package com.validation.service;

import com.validation.exception.NotFoundException;
import com.validation.model.Document;
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

    public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }

    public Document findById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Документ с id:" + id + " не существует."));
    }
}
