package com.uwu.tas.controller;

import com.uwu.tas.dto.StudentDto;
import com.uwu.tas.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    //http://localhost:8080/student/save
    @PostMapping(value = "/save")
    public ResponseEntity<String> saveStudent(@RequestBody StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        return ResponseEntity.ok("Student saved successfully");
    }

    //http://localhost:8080/student/all
    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }

    //http://localhost:8080/student/by-id
    @GetMapping(value = "/by-id")
    public ResponseEntity<StudentDto> getStudentById(@RequestParam(value = "id") long id) {
        StudentDto studentById = studentService.getStudentById(id);
        return ResponseEntity.ok(studentById);
    }

}
