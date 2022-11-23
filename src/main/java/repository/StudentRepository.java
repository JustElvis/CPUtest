package repository;

import java.util.List;
import model.Student;
import model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findAllByTeacher(Teacher teacher);
}
