package service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import model.Student;
import model.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.StudentRepository;
import repository.TeacherRepository;
import service.StudentService;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findStudentByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Student> getAllByTeacherId(Long teacherId, Pageable pageable) {
        return studentRepository.findAllByTeacher(teacherRepository.findById(teacherId).orElseThrow(() ->
                new RuntimeException("No such teacher with id -> " + teacherId)), pageable);
    }

    @Override
    public void attachTeacherToStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("No such student with id -> " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new RuntimeException("No such teacher with id -> " + teacherId));
        student.getTeachers().add(teacher);
        studentRepository.save(student);
    }
}
