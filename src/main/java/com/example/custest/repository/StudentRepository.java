package com.example.custest.repository;

import com.example.custest.model.Student;
import com.example.custest.model.Teacher;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findAllByTeachersContains(Teacher teacher, Pageable pageable);
}
