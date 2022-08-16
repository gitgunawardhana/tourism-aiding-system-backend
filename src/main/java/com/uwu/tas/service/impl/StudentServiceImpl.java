package com.uwu.tas.service.impl;

import com.uwu.tas.dto.StudentDto;
import com.uwu.tas.entity.Student;
import com.uwu.tas.repository.StudentRepository;
import com.uwu.tas.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void saveStudent(StudentDto dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());

        studentRepository.save(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {

        List<Student> all = studentRepository.findAll();

        List<StudentDto> studentDtos = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            Student student = all.get(i);

            StudentDto dto = new StudentDto(student.getName(), student.getAge());

            studentDtos.add(dto);
        }

        return studentDtos;
    }
}
