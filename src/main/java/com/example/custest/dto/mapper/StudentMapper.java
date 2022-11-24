package com.example.custest.dto.mapper;

import com.example.custest.dto.request.StudentRequestDto;
import com.example.custest.dto.response.StudentResponseDto;
import com.example.custest.model.Student;
import com.example.custest.model.Teacher;
import com.example.custest.repository.TeacherRepository;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper {
    private final TeacherRepository teacherRepository;

    public StudentResponseDto mapToDto(Student student) {
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setAge(student.getAge());
        responseDto.setSpeciality(student.getSpeciality());
        responseDto.setEmail(student.getEmail());
        responseDto.setTeachersIds(student.getTeachers()
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }

    public Student mapToModel(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setAge(requestDto.getAge());
        student.setSpeciality(requestDto.getSpeciality());
        student.setEmail(requestDto.getEmail());
        student.setTeachers(teacherRepository.findAllById(requestDto.getTeachersIds()));
        return student;
    }
}
