package com.validation.loader;

import com.validation.configuration.PasswordEncoderConfiguration;
import com.validation.model.Document;
import com.validation.model.Role;
import com.validation.model.User;
import com.validation.model.Verification;
import com.validation.model.documents.DocumentType;
import com.validation.model.documents.Passport;
import com.validation.repository.DocumentRepository;
import com.validation.repository.RoleRepository;
import com.validation.repository.UserRepository;
import com.validation.repository.documents.DocumentTypeRepository;
import com.validation.repository.documents.PassportRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DatabaseLoader implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PassportRepository passportRepository;
    DocumentRepository documentRepository;
    DocumentTypeRepository documentTypeRepository;

    PasswordEncoderConfiguration passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {

        var roles = registerDefaultUserRoles();

        var admin = registerDefaultUserIfNotExist(roles);

        registerPassportIfNotExist(admin);
    }

    private void registerPassportIfNotExist(User user) {
        if (passportRepository.findBySerialAndNumber("2020", "123123").isEmpty()) {
            var document = registerDocumentIfNotExist(user);
            var docTypes = registerDefaultDocumentTypesIfNotExist();
            passportRepository.save(
                    Passport.builder()
                            .id(1L)
                            .serial("2020")
                            .number("123123")
                            .issueDate(LocalDate.now())
                            .departmentCode("123-123")
                            .gender("Мужской")
                            .birthDate(LocalDate.now().minusYears(21L))
                            .birthPlace("Воронеж")
                            .firstName("Никита")
                            .lastName("Скулыбердин")
                            .middleName("Юрьевич")
                            .document(document)
                            .documentType(
                                    docTypes.stream()
                                            .filter(documentType -> documentType.getTypeName().equals("Passport"))
                                            .findAny().orElse(DocumentType.builder().typeName("Passport").build())
                            )
                            .build()
            );
        }
    }

    private Document registerDocumentIfNotExist(User admin) {
        if (documentRepository.findByUserId(admin.getId()).isEmpty()) {
            Document document = Document.builder()
                    .user(admin)
                    .verification(
                            Verification.builder()
                                    .expirationDate(LocalDate.now().plusYears(1))
                                    .build()
                    )
                    .build();

            return documentRepository.save(document);
        } else {
            return documentRepository.findByUserId(admin.getId()).get(0);
        }
    }

    private List<Role> registerDefaultUserRoles() {
        var roles = roleRepository.findAll();

        roles.add(roleRepository.save(new Role("ROLE_USER", 1L)));
        roles.add(roleRepository.save(new Role("ROLE_ADMIN", 2L)));

        return roles;
    }

    private User registerDefaultUserIfNotExist(List<Role> roles) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@mail.ru")
                    .password(passwordEncoder.getBCryptPasswordEncoder().encode("admin"))
                    .build();

            admin.setRoles(roles);

            return userRepository.save(admin);
        } else {
            return userRepository.findByUsername("admin").get();
        }
    }

    private List<DocumentType> registerDefaultDocumentTypesIfNotExist() {
        var docTypes = documentTypeRepository.findAll();

        if (documentTypeRepository.findByTypeName("Passport").isEmpty()) {
            docTypes.add(
                    documentTypeRepository.save(
                            DocumentType.builder()
                                    .typeName("Passport")
                                    .build()
                    )
            );
        }

        return docTypes;
    }
}

