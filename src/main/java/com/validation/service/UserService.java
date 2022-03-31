package com.validation.service;

import com.validation.dto.request.RegistrationRequestDto;
import com.validation.exception.NotFoundException;
import com.validation.model.Role;
import com.validation.model.User;
import com.validation.repository.RoleRepository;
import com.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// todo: Add changePassword etc.
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким именем не найден"));
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                        () -> new NotFoundException("Пользователь с id:" + id + " не существует.")
        );
    }

    public void register(RegistrationRequestDto requestDto) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(userRoles);
        user.setDocuments(new ArrayList<>());

        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
