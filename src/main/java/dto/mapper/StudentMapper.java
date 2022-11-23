package dto.mapper;

import dto.request.StudentRequestDto;
import dto.response.StudentResponseDto;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import model.Student;
import model.Teacher;
import org.springframework.stereotype.Component;
import repository.StudentRepository;
import repository.TeacherRepository;

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
