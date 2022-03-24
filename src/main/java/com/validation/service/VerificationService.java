package com.validation.service;

import java.util.List;
import java.util.Optional;

import com.validation.model.Verification;
import com.validation.repository.VerificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    public List<Verification> findAll() {
        return this.verificationRepository.findAll();
    }

    public Verification add(Verification verification) {
        return verificationRepository.save(verification);
    }

    public boolean delete(Long id) {
        verificationRepository.deleteById(id);
        Optional<Verification> result = verificationRepository.findById(id);
        return result.isEmpty();
    }

    public Verification update(Long id, Verification verification) {
        Optional<Verification> toUpdate = verificationRepository.findById(id);
        if (toUpdate.isPresent()) {
            toUpdate.get().setExpirationDate(verification.getExpirationDate());
            toUpdate.get().setUrl(verification.getUrl());
            return verificationRepository.save(verification);
        }
        else {
            return null;
        }
    }

    public Verification findById(Long id) {
        return null;
    }

}