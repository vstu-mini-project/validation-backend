package com.validation.service.documents;

import com.validation.dto.documents.PassportDto;
import com.validation.exception.NotFoundException;
import com.validation.model.Document;
import com.validation.model.documents.DocumentType;
import com.validation.model.documents.Passport;
import com.validation.repository.UserRepository;
import com.validation.repository.documents.DocumentTypeRepository;
import com.validation.repository.documents.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportService {
    private final PassportRepository passportRepository;
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public PassportService(PassportRepository passportRepository, UserRepository userRepository, DocumentTypeRepository documentTypeRepository) {
        this.passportRepository = passportRepository;
        this.userRepository = userRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    public Passport getPassport(Long id) {
        return passportRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Паспорт с id:" + id + " не существует.")
        );
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Passport savePassport(PassportDto request) {
        if (passportRepository.findBySerialAndNumber(request.getSerial(), request.getNumber()).isPresent())
            return null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Document document = new Document();
        document.setUser(userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("Ошибка: пользователь " + username + " не существует")
        ));
        document.setVerification(null);

        DocumentType documentType
                = documentTypeRepository.findByTypeName("Passport").orElseThrow(
                        () -> new NotFoundException("Ошибка: тип документа " + "Passport" + " не существует.")
        );
        Passport passport = PassportDto.toPassport(request);
        passport.setDocument(document);
        passport.setDocumentType(documentType);
        return passportRepository.save(passport);
    }
}
