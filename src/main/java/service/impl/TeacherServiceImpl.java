package service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import model.Student;
import model.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.StudentRepository;
import repository.TeacherRepository;
import service.TeacherService;

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
    public List<Teacher> getAllByStudentId(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("No such student with id -> " + studentId)).getTeachers();
    }

    @Override
    public void attachStudentToTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("No such student with id -> " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new RuntimeException("No such teacher with id -> " + teacherId));
        student.getTeachers().add(teacher);
        studentRepository.save(student);
    }
}
