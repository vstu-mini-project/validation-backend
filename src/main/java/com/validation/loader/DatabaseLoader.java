package com.validation.loader;

import com.validation.model.Student;
import com.validation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Autowired
    public DatabaseLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.studentRepository.save(new Student("Frodo", "Baggins", "ring bearer"));
        this.studentRepository.save(new Student("Иван", "Иванов", "Тестовый студент"));
    }
}
