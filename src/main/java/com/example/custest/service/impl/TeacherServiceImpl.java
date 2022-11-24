package com.example.custest.service.impl;

import com.example.custest.model.Student;
import com.example.custest.model.Teacher;
import com.example.custest.repository.StudentRepository;
import com.example.custest.repository.TeacherRepository;
import com.example.custest.service.TeacherService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getByFirstNameAndLastName(String firstName, String lastName) {
        return teacherRepository.findTeacherByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll(PageRequest pageRequest) {
        return teacherRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Teacher> getAllByStudentId(Long studentId, Pageable pageable) {
        return teacherRepository.findAllByStudentsContains(studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("No such student with id -> "
                        + studentId)), pageable);
    }

    @Override
    public void attachStudentToTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("No such student with id -> " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new RuntimeException("No such teacher with id -> " + teacherId));
        teacher.getStudents().add(student);
        teacherRepository.save(teacher);
    }

    @Override
    public void removeStudentFromTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("No such student with id -> " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new RuntimeException("No such teacher with id -> " + teacherId));
        teacher.getStudents().remove(student);
        teacherRepository.save(teacher);
    }
}
