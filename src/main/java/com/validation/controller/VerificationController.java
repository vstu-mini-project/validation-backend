package com.validation.controller;


import com.validation.model.Verification;
import com.validation.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/verifications/", produces = APPLICATION_JSON_VALUE)
public class VerificationController {

    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }
    @GetMapping
    public ResponseEntity<List<Verification>>getVerifications() {
        return ResponseEntity.ok(verificationService.findAll());
    }

    @PostMapping(value = "{id}")
    public ResponseEntity<Verification> getVerificationById(@PathVariable(name = "id") Long id) {
        Verification verification = verificationService.findById(id);
        if (verification == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(verification, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Verification> saveVerification(@RequestBody Verification verification) {
        Verification createdVerification = verificationService.add(verification);
        if (createdVerification == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(createdVerification);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Verification> update(@PathVariable(name = "id") Long id, Verification verification) {
        if (verificationService.update(id, verification) != null)
            return ResponseEntity.ok(verificationService.findById(id));
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long> delete (@PathVariable(name = "id") Long id) {
        if (verificationService.delete(id))
            return ResponseEntity.ok(id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}