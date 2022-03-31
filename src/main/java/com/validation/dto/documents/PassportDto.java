package com.validation.dto.documents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.validation.model.documents.Passport;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PassportDto {
    private Long id;

    private String serial;
    private String number;

    private LocalDate issueDate;
    private String departmentCode;

    private String gender;
    private LocalDate birthDate;
    private String birthPlace;

    private String firstName;
    private String lastName;
    private String middleName;


    public static Passport toPassport(PassportDto dto) {
        Passport passport = new Passport();
        passport.setId(dto.getId());
        passport.setSerial(dto.getSerial());
        passport.setNumber(dto.getNumber());
        passport.setIssueDate(dto.getIssueDate());
        passport.setDepartmentCode(dto.getDepartmentCode());
        passport.setGender(dto.getGender());
        passport.setBirthDate(dto.getBirthDate());
        passport.setBirthPlace(dto.getBirthPlace());
        passport.setFirstName(dto.getFirstName());
        passport.setLastName(dto.getLastName());
        passport.setMiddleName(dto.getMiddleName());
        return passport;
    }

    public static PassportDto fromPassport(Passport passport) {
        PassportDto dto = new PassportDto();
        dto.setId(passport.getId());
        dto.setSerial(passport.getSerial());
        dto.setNumber(passport.getNumber());
        dto.setIssueDate(passport.getIssueDate());
        dto.setDepartmentCode(passport.getDepartmentCode());
        dto.setGender(passport.getGender());
        dto.setBirthDate(passport.getBirthDate());
        dto.setBirthPlace(passport.getBirthPlace());
        dto.setFirstName(passport.getFirstName());
        dto.setLastName(passport.getLastName());
        dto.setMiddleName(passport.getMiddleName());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
