package com.validation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", updatable = false, nullable = false)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() { }

    public Role(String name, Long id) {
     this.name = name;
     this.id = id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
