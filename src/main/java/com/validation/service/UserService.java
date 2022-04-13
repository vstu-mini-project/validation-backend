package com.validation.service;

import com.validation.dto.documents.request.RegistrationRequestDto;
import com.validation.exception.NotFoundException;
import com.validation.model.Role;
import com.validation.model.User;
import com.validation.repository.RoleRepository;
import com.validation.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserService {

    // TODO: Add changePassword etc.

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким именем не найден"));
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                () -> new NotFoundException("Пользователь с id:" + id + " не существует.")
        );
    }

    public void registerUser(RegistrationRequestDto requestDto) {

        Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new NotFoundException("Ошибка присвоения стандартной роли USER.")
        );

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        user.setRoles(userRoles);

        userRepository.save(user);
    }

    public void deleteUser(@NotNull Long id) {
        userRepository.deleteById(id);
    }
}
