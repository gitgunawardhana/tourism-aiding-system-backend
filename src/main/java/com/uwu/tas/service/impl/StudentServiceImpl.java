package com.uwu.tas.service.impl;

import com.uwu.tas.dto.StudentDto;
import com.uwu.tas.entity.Student;
import com.uwu.tas.repository.StudentRepository;
import com.uwu.tas.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void saveStudent(StudentDto studentDto) {

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setAddress(studentDto.getAddress());

        studentRepository.save(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> all = studentRepository.findAll();

        List<StudentDto> studentDtoList = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            Student s = all.get(i);

            StudentDto dto = new StudentDto();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setAge(s.getAge());
            dto.setAddress(s.getAddress());

            studentDtoList.add(dto);
        }

        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        boolean present = optionalStudent.isPresent();
        if (present) {

            Student s = optionalStudent.get();

            StudentDto dto = new StudentDto();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setAge(s.getAge());
            dto.setAddress(s.getAddress());

            return dto;
        } else {
            return null;
        }
    }
}
