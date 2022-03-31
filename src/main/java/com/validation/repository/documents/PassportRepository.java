package com.validation.repository.documents;

import com.validation.model.documents.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findBySerialAndNumber(String serial, String number);
}
