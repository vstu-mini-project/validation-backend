package com.validation.service;

import java.util.List;
import java.util.Optional;

import com.validation.exception.ArgumentWasNullException;
import com.validation.exception.NotFoundException;
import com.validation.model.Verification;
import com.validation.repository.VerificationRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VerificationService {

    VerificationRepository verificationRepository;

    public Verification findById(Long id) {
        return verificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Верификация с id:" + id + " не существует.")
        );
    }

    public List<Verification> findAllVerifications() {
        return this.verificationRepository.findAll();
    }

    public Verification registerVerification(Verification verification) {
        return verificationRepository.save(verification);
    }

    public void deleteVerification(Long id) {
        verificationRepository.deleteById(id);
    }

    public Verification updateVerification(Long id, Verification verification) {

        Optional<Verification> toUpdate = verificationRepository.findById(id);

        if (toUpdate.isEmpty())
            throw new NotFoundException("Верификация с id:" + id + " не существует.");

        if (Optional.ofNullable(verification).isEmpty())
            throw new ArgumentWasNullException("Данные для обновления были пусты.");

        toUpdate.get().setExpirationDate(verification.getExpirationDate());
        toUpdate.get().setUrl(verification.getUrl());

        return verificationRepository.save(verification);
    }
}
