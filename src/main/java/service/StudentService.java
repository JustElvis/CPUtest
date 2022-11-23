package service;

import java.util.List;
import model.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Student save(Student student);
    Student getByFirstNameAndLastName(String firstName, String lastName);
    void deleteById(Long id);
    List<Student> getAll(PageRequest pageRequest);
    List<Student> getAllByTeacherId(Long teacherId, Pageable pageable);
    void attachTeacherToStudent(Long studentId, Long teacherId);
}
