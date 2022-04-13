package com.validation.service.documents;

import com.validation.dto.documents.PassportDto;
import com.validation.exception.AlreadyExistException;
import com.validation.exception.NotFoundException;
import com.validation.model.Document;
import com.validation.model.Verification;
import com.validation.model.documents.DocumentType;
import com.validation.model.documents.Passport;
import com.validation.repository.UserRepository;
import com.validation.repository.documents.DocumentTypeRepository;
import com.validation.repository.documents.PassportRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PassportService {

    DocumentTypeRepository documentTypeRepository;
    PassportRepository passportRepository;
    UserRepository userRepository;

    public Passport getPassport(Long id) {
        return passportRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Паспорт с id:" + id + " не существует.")
        );
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Passport registerPassport(PassportDto request) {

        if (passportRepository.findBySerialAndNumber(request.getSerial(), request.getNumber()).isPresent())
            throw new AlreadyExistException("Данные не корректны.");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Document document = Document.builder()
                .user(
                        userRepository.findByUsername(username).orElseThrow(
                                () -> new NotFoundException("Ошибка: пользователь " + username + " не существует")
                        )
                )
                .verification(Verification.builder().expirationDate(LocalDate.MAX).build())
                .build();

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
