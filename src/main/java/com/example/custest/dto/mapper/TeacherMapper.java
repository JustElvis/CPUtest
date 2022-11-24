package com.example.custest.dto.mapper;

import com.example.custest.dto.request.TeacherRequestDto;
import com.example.custest.dto.response.TeacherResponseDto;
import com.example.custest.model.Student;
import com.example.custest.model.Teacher;
import com.example.custest.repository.StudentRepository;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherMapper {
    private final StudentRepository studentRepository;

    public TeacherResponseDto mapToDto(Teacher teacher) {
        TeacherResponseDto responseDto = new TeacherResponseDto();
        responseDto.setId(teacher.getId());
        responseDto.setFirstName(teacher.getFirstName());
        responseDto.setLastName(teacher.getLastName());
        responseDto.setAge(teacher.getAge());
        responseDto.setSubject(teacher.getSubject());
        responseDto.setEmail(teacher.getEmail());
        responseDto.setStudentsIds(teacher.getStudents()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }

    public Teacher mapToModel(TeacherRequestDto requestDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(requestDto.getFirstName());
        teacher.setLastName(requestDto.getLastName());
        teacher.setAge(requestDto.getAge());
        teacher.setSubject(requestDto.getSubject());
        teacher.setEmail(requestDto.getEmail());
        teacher.setStudents(studentRepository.findAllById(requestDto.getStudentsIds()));
        return teacher;
    }
}
