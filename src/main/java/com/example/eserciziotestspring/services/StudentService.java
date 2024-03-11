package com.example.eserciziotestspring.services;

import com.example.eserciziotestspring.entities.Student;
import com.example.eserciziotestspring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student changeIsWorking (Long id, Boolean isWorking){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            student.get().setIsWorking(isWorking);
            return studentRepository.save(student.get());

        }
        return null;
    }
}
