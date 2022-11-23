package dto.mapper;

import dto.request.TeacherRequestDto;
import dto.response.TeacherResponseDto;
import model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public TeacherResponseDto mapToDto(Teacher teacher) {
        TeacherResponseDto responseDto = new TeacherResponseDto();
        responseDto.setId(teacher.getId());
        responseDto.setFirstName(teacher.getFirstName());
        responseDto.setLastName(teacher.getLastName());
        responseDto.setAge(teacher.getAge());
        responseDto.setSubject(teacher.getSubject());
        responseDto.setEmail(teacher.getEmail());
        return responseDto;
    }

    public Teacher mapToModel(TeacherRequestDto requestDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(requestDto.getFirstName());
        teacher.setLastName(requestDto.getLastName());
        teacher.setAge(requestDto.getAge());
        teacher.setSubject(requestDto.getSubject());
        teacher.setEmail(requestDto.getEmail());
        return teacher;
    }
}
