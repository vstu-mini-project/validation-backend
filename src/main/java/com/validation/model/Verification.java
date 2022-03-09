package com.validation.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "verifications")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "verification_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    private String url;

    public Verification() { }

    @Override
    public String toString() {
        return "Verification{" +
                "id=" + getId() +
                ", expirationDate=" + getExpirationDate() +
                ", url='" + getUrl() + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
