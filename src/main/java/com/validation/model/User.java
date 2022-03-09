package com.validation.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Set<UserRole> roles;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Document> documents;

    public User() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.getId() == user.getId()
                && this.getUsername().equals(user.getUsername())
                && this.getEmail().equals(user.getEmail())
                && this.password.equals(user.getPassword())
                && Objects.equals(this.getRoles(), user.getRoles())
                && Objects.equals(this.getDocuments(), user.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId(),
                this.getUsername(),
                this.getEmail(),
                this.getPassword(),
                this.getRoles(),
                this.getDocuments()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
