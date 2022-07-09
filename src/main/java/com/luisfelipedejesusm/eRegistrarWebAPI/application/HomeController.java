package com.luisfelipedejesusm.eRegistrarWebAPI.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/eregistrar/api/student")
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("list")
    public List<Student> getListOfStudents(){
        return studentService.getListOfStudents();
    }

    @PostMapping("register")
    public ResponseEntity<?> registerNewStudent(@Valid @RequestBody Student student){
        Long studentId = studentService.save(student);
        return ResponseEntity.ok(studentId);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        Student student = studentService.getStudentById(id);
        if(student != null) return ResponseEntity.ok(student);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }
}
