package com.validation.model.documents;

import com.validation.model.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student_certificates")
public class StudentCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_certificate_id")
    private Long id;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    @Column(nullable = false)
    private String number;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;

    @Column(nullable = false)
    private String studyGroup;
    @Column(name = "faculty", nullable = false)
    private String faculty;
    @Column(name = "study_form", nullable = false)
    private String studyForm;

    @Column(name = "study_from_date", nullable = false)
    private LocalDate studyFromDate;
    @Column(name = "study_to_date", nullable = false)
    private LocalDate studyToDate;

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
        StudentCertificate that = (StudentCertificate) o;
        return this.getId().equals(that.getId())
                && this.getIssueDate().equals(that.getIssueDate())
                && this.getNumber().equals(that.getNumber())
                && this.getFirstName().equals(that.getFirstName())
                && this.getLastName().equals(that.getLastName())
                && Objects.equals(getMiddleName(), that.getMiddleName())
                && this.getStudyGroup().equals(that.getStudyGroup())
                && this.getFaculty().equals(that.getFaculty())
                && this.getStudyForm().equals(that.getStudyForm())
                && this.getStudyFromDate().equals(that.getStudyFromDate())
                && this.getStudyToDate().equals(that.getStudyToDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getIssueDate(),
                getNumber(),
                getFirstName(),
                getLastName(),
                getMiddleName(),
                getStudyGroup(),
                getFaculty(),
                getStudyForm(),
                getStudyFromDate(),
                getStudyToDate()
        );
    }

    @Override
    public String toString() {
        return "StudentCertificate{" +
                "id=" + getId() +
                ", issueDate=" + getIssueDate() +
                ", number='" + getNumber() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", middleName='" + getMiddleName() + '\'' +
                ", group='" + getStudyGroup() + '\'' +
                ", faculty='" + getFaculty() + '\'' +
                ", studyForm='" + getStudyForm() + '\'' +
                ", studyFromDate=" + getStudyFromDate() +
                ", studyToDate=" + getStudyToDate() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(String studyForm) {
        this.studyForm = studyForm;
    }

    public LocalDate getStudyFromDate() {
        return studyFromDate;
    }

    public void setStudyFromDate(LocalDate studyFromDate) {
        this.studyFromDate = studyFromDate;
    }

    public LocalDate getStudyToDate() {
        return studyToDate;
    }

    public void setStudyToDate(LocalDate studyToDate) {
        this.studyToDate = studyToDate;
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
