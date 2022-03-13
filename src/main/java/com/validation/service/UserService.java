package com.validation.service;

import com.validation.model.Document;
import com.validation.model.Role;
import com.validation.model.User;
import com.validation.repository.RoleRepository;
import com.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// TODO Change to interface and add implementation
// TODO Add delete method
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findByUsername(String username) {
        // TODO Валидация логина (только латинские буквы и цифры)
        // TODO Обработка NULL
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        // TODO Обработка NULL
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setDocuments(new ArrayList<Document>());

        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
