package com.validation.controller;

import com.validation.dto.DocumentDto;
import com.validation.dto.documents.PassportDto;
import com.validation.model.documents.DocumentType;
import com.validation.service.DocumentService;
import com.validation.service.documents.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/documents", produces = APPLICATION_JSON_VALUE)
@CrossOrigin
public class DocumentController {
    private final DocumentService documentService;
    private final PassportService passportService;

    @Autowired
    public DocumentController(DocumentService documentService, PassportService passportService) {
        this.documentService = documentService;
        this.passportService = passportService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getDocument(DocumentType documentType) {
        return ResponseEntity.ok(
                documentService.findAllDocuments().stream()
                    .map(DocumentDto::fromDocument)
                    .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(
                DocumentDto.fromDocument(documentService.findById(id))
        );
    }

    @GetMapping(value = "passports")
    public ResponseEntity<List<PassportDto>> getAllPassports() {
        return ResponseEntity.ok(
                passportService.getAllPassports().stream()
                    .map(PassportDto::fromPassport)
                    .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "passports/{id}")
    public ResponseEntity<PassportDto> getAllPassports(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(
                PassportDto.fromPassport(passportService.getPassport(id))
        );
    }

    @PostMapping(value = "passports")
    public ResponseEntity<PassportDto> addPassport(@RequestBody PassportDto passportRequest) {
        return ResponseEntity.ok(
                PassportDto.fromPassport(passportService.registerPassport(passportRequest))
        );
    }


}
