package com.validation.repository.documents;

import com.validation.model.documents.StudentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCertificateRepository extends JpaRepository<StudentCertificate, Long> {
}
