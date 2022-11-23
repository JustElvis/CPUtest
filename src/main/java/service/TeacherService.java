package service;

import java.util.List;
import model.Student;
import model.Teacher;
import org.springframework.data.domain.PageRequest;

public interface TeacherService {
    Teacher save(Teacher teacher);
    Teacher getByFirstNameAndLastName(String firstName, String lastName);
    void deleteById(Long id);
    List<Teacher> getAll(PageRequest pageRequest);
    List<Teacher> getAllByStudentId(Long studentId);
    void attachStudentToTeacher(Long studentId, Long teacherId);
}
