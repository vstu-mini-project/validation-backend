package com.validation.service;

import com.validation.model.User;
import com.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findByUsername(String username) {
        // TODO Валидация логина (только латинские буквы и цифры)
        return this.userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }
}
