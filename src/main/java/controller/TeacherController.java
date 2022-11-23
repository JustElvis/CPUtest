package controller;

import dto.mapper.TeacherMapper;
import dto.request.TeacherRequestDto;
import dto.response.TeacherResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import model.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.TeacherService;
import util.SortUtil;

@RestController
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final SortUtil sortUtil;

    @PostMapping
    public TeacherResponseDto create(@RequestBody TeacherRequestDto requestDto) {
        Teacher teacher = teacherMapper.mapToModel(requestDto);
        return teacherMapper.mapToDto(teacherService.save(teacher));
    }

    @GetMapping
    public TeacherResponseDto getByFirstNameAndLastName(@RequestParam String firstName,
                                                        @RequestParam String lastName) {
        Teacher teacher = teacherService.getByFirstNameAndLastName(firstName, lastName);
        return teacherMapper.mapToDto(teacher);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TeacherResponseDto updateById(@PathVariable Long id,
                                         @RequestBody TeacherRequestDto requestDto) {
        Teacher teacher = teacherMapper.mapToModel(requestDto);
        teacher.setId(id);
        return teacherMapper.mapToDto(teacherService.save(teacher));
    }

    @GetMapping
    public List<TeacherResponseDto> getAll(@RequestParam(defaultValue = "20") Integer count,
                                           @RequestParam(defaultValue = "0") Integer page,
                                           @RequestParam(defaultValue = "firstName") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count, sortUtil.getSort(sortBy));
        return teacherService.getAll(pageRequest)
                .stream()
                .map(teacherMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<TeacherResponseDto> getAllByStudentId(@RequestParam Long studentId) {
        return teacherService.getAllByStudentId(studentId)
                .stream()
                .map(teacherMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/attach/student")
    void attachStudentToTeacher(@PathVariable Long id,
                                @RequestParam Long studentId) {
        teacherService.attachStudentToTeacher(studentId, id);
    }
}
