package service;

import java.util.List;
import model.Student;
import model.Teacher;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Student save(Student student);
    Student getByFirstNameAndLastName(String firstName, String lastName);
    void deleteById(Long id);
    List<Student> getAll(PageRequest pageRequest);
    List<Student> getAllByTeacherId(Long teacherId);
    void attachTeacherToStudent(Long studentId, Long teacherId);
}
