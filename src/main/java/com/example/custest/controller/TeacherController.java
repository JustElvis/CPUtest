package com.example.custest.controller;

import com.example.custest.dto.mapper.TeacherMapper;
import com.example.custest.dto.request.TeacherRequestDto;
import com.example.custest.dto.response.TeacherResponseDto;
import com.example.custest.model.Teacher;
import com.example.custest.service.TeacherService;
import com.example.custest.util.SortUtil;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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

@RestController
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final SortUtil sortUtil;

    @PostMapping
    @ApiOperation(value = "Create a new teacher")
    public TeacherResponseDto create(@RequestBody TeacherRequestDto requestDto) {
        Teacher teacher = teacherMapper.mapToModel(requestDto);
        return teacherMapper.mapToDto(teacherService.save(teacher));
    }

    @GetMapping("/name")
    @ApiOperation(value = "Get teacher by firstName and lastName")
    public TeacherResponseDto getByFirstNameAndLastName(@RequestParam String firstName,
                                                        @RequestParam String lastName) {
        Teacher teacher = teacherService.getByFirstNameAndLastName(firstName, lastName);
        return teacherMapper.mapToDto(teacher);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete teacher by id")
    void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update teacher by id")
    public TeacherResponseDto updateById(@PathVariable Long id,
                                         @RequestBody TeacherRequestDto requestDto) {
        Teacher teacher = teacherMapper.mapToModel(requestDto);
        teacher.setId(id);
        return teacherMapper.mapToDto(teacherService.save(teacher));
    }

    @GetMapping
    @ApiOperation(value = "Find all teachers with pagination and sort")
    public List<TeacherResponseDto> getAll(
            @RequestParam(defaultValue = "20") Integer count,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "firstName") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count, sortUtil.getSort(sortBy));
        return teacherService.getAll(pageRequest)
                .stream()
                .map(teacherMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/student")
    @ApiOperation(value = "Find all teacher by studentId with pagination and sort")
    public List<TeacherResponseDto> getAllByStudentId(
            @RequestParam(defaultValue = "20") Integer count,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam Long studentId) {
        PageRequest pageRequest = PageRequest.of(page, count, sortUtil.getSort(sortBy));
        return teacherService.getAllByStudentId(studentId, pageRequest)
                .stream()
                .map(teacherMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/attach/student")
    @ApiOperation(value = "Attach student to teacher")
    void attachStudentToTeacher(@PathVariable Long id,
                                @RequestParam Long studentId) {
        teacherService.attachStudentToTeacher(studentId, id);
    }
}
