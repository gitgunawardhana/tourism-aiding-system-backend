package com.uwu.tas.service;

import com.uwu.tas.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    void saveStudent(StudentDto dto);

    List<StudentDto> getAllStudents();

}
