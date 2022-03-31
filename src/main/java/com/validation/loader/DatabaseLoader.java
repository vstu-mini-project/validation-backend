package com.validation.loader;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PassportRepository passportRepository;
    private final DocumentRepository documentRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, RoleRepository roleRepository, PassportRepository passportRepository, DocumentRepository documentRepository, DocumentTypeRepository documentTypeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passportRepository = passportRepository;
        this.documentRepository = documentRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        // Create user if not exist
        roleRepository.save(new Role("ROLE_USER", 1L));
        roleRepository.save(new Role("ROLE_ADMIN", 2L));

        if (!userRepository.findByUsername("admin").isPresent()) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@mail.ru");
            user.setPassword("$2a$10$t4l8ea.54Mw6ZQpS1fHtMOrsaHBq/toNvNBQdEYLyOx37RNN6JgT2");
            userRepository.save(user);
        }

        // Create document with type Passport if not exist
        if (passportRepository.findBySerialAndNumber("2020", "123123").isEmpty()) {
            DocumentType type = new DocumentType();
            type.setId(19L);
            type.setTypeName("Passport");

            Document doc = new Document();
            doc.setId(21L);
            doc.setVerification(new Verification());
            doc.setUser(userRepository.findByUsername("admin").get());

            type = documentTypeRepository.findById(19L).orElse(type);
            doc = documentRepository.findById(21L).orElse(doc);

            passportRepository.save(new Passport(
                    1L,
                    "2020",
                    "123123",
                    LocalDate.now(),
                    "360-001",
                    "Мужской",
                    LocalDate.now().minusYears(21L),
                    "Воронеж",
                    "Никита",
                    "Скулыбердин",
                    "Юрьевич",
                    doc,
                    type
            ));
        }
    }
}
