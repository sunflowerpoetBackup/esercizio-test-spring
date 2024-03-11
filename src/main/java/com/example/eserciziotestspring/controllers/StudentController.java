package com.example.eserciziotestspring.controllers;

import com.example.eserciziotestspring.entities.Student;
import com.example.eserciziotestspring.repositories.StudentRepository;
import com.example.eserciziotestspring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;
    @Autowired
    public StudentRepository studentRepository;

    @PostMapping("/create-new-student")
    public Student createNewStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/get-all-students")
    public List<Student> listOfStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/get-student-by-id/{id}")
    public Optional<Student> findStudentById(@PathVariable Long id){
        return studentRepository.findById(id);
    }

    @PatchMapping("/update-value/{id}")
    public Student updateValue(@PathVariable Long id, @RequestParam Boolean isWorking){
        return studentService.changeIsWorking(id, isWorking);
    }

    @DeleteMapping("/delete-student/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentRepository.deleteById(id);
    }
}
