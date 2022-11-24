package com.example.custest.service;

import com.example.custest.model.Teacher;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Teacher save(Teacher teacher);

    Teacher getByFirstNameAndLastName(String firstName, String lastName);

    void deleteById(Long id);

    List<Teacher> getAll(PageRequest pageRequest);

    List<Teacher> getAllByStudentId(Long studentId, Pageable pageable);

    void attachStudentToTeacher(Long studentId, Long teacherId);

    void removeStudentFromTeacher(Long studentId, Long teacherId);
}
