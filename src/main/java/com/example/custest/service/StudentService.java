package com.example.custest.service;

import com.example.custest.model.Student;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Student save(Student student);

    Student getByFirstNameAndLastName(String firstName, String lastName);

    void deleteById(Long id);

    List<Student> getAll(PageRequest pageRequest);

    List<Student> getAllByTeacherId(Long teacherId, Pageable pageable);

    void attachTeacherToStudent(Long studentId, Long teacherId);

    void removeTeacherFromStudent(Long studentId, Long teacherId);
}
