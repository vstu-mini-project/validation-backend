package com.validation.model.documents;

import com.validation.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Builder
@Entity
@Table(name = "passports")
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passport_id")
    private Long id;

    @Column(length = 4 , nullable = false)
    private String serial;
    @Column(length = 6, nullable = false)
    private String number;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    @Column(name = "department_code", nullable = false)
    private String departmentCode;

    @Column(length = 10, nullable = false)
    private String gender;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return getId().equals(passport.getId())
                && getSerial().equals(passport.getSerial())
                && getNumber().equals(passport.getNumber())
                && getIssueDate().equals(passport.getIssueDate())
                && getDepartmentCode().equals(passport.getDepartmentCode())
                && getGender().equals(passport.getGender())
                && getBirthDate().equals(passport.getBirthDate())
                && getBirthPlace().equals(passport.getBirthPlace())
                && getFirstName().equals(passport.getFirstName())
                && getLastName().equals(passport.getLastName())
                && Objects.equals(getMiddleName(), passport.getMiddleName())
                && getDocument().equals(passport.getDocument())
                && getDocumentType().equals(passport.getDocumentType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getSerial(),
                getNumber(),
                getIssueDate(),
                getDepartmentCode(),
                getGender(),
                getBirthDate(),
                getBirthPlace(),
                getFirstName(),
                getLastName(),
                getMiddleName(),
                getDocument(),
                getDocumentType()
        );
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id='" + getId() + '\'' +
                ", serial='" + getSerial() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", issueDate=" + getIssueDate() +
                ", departmentCode='" + getDepartmentCode() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", birthPlace='" + getBirthPlace() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", middleName='" + getMiddleName() + '\'' +
                ", document=" + getDocument().toString() +
                ", documentType=" + getDocumentType().toString() +
                '}';
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
