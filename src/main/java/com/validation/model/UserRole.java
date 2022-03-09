package com.validation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
