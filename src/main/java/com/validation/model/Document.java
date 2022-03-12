package com.validation.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "verification_id")
    private Verification verification;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Document() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(this.getId(), document.getId())
                && Objects.equals(this.getVerification(), document.getVerification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId(),
                this.getVerification()
        );
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + getId() +
                ", verification=" + getVerification().toString() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
