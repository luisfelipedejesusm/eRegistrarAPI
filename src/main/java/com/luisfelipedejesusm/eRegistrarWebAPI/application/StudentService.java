package com.luisfelipedejesusm.eRegistrarWebAPI.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getListOfStudents() {
        return repository.findAll();
    }

    public Long save(Student student) {
        return repository.save(student).getId();
    }

    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteStudentById(Long id) {
        repository.deleteById(id);
    }
}
