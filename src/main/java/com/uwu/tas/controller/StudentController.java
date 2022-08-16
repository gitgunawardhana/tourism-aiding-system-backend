package com.uwu.tas.controller;

import com.uwu.tas.dto.StudentDto;
import com.uwu.tas.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    //http://localhost:8080/student/save
    @PostMapping(value = "/save")
    public ResponseEntity saveStudent(@RequestBody StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        return ResponseEntity.ok("Student Saved Successfully");
    }

    //http://localhost:8080/student/all
    @GetMapping(value = "/all")
    public ResponseEntity getAllStudents() {
        List<StudentDto> allStudents = studentService.getAllStudents();

        return ResponseEntity.ok(allStudents);
    }
}

//http://localhost:8080/student