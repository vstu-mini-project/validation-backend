package com.validation.loader;

import com.validation.model.Role;
import com.validation.repository.RoleRepository;
import com.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        roleRepository.save(new Role("ROLE_USER", 1L));
        roleRepository.save(new Role("ROLE_ADMIN", 2L));


    }
}
