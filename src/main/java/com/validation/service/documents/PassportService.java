package com.validation.service.documents;

import com.validation.repository.documents.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    private final PassportRepository passportRepository;

    @Autowired
    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

}
