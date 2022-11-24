package com.example.custest.controller;

import com.example.custest.dto.mapper.StudentMapper;
import com.example.custest.dto.request.StudentRequestDto;
import com.example.custest.dto.response.StudentResponseDto;
import com.example.custest.model.Student;
import com.example.custest.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final SortUtil sortUtil;

    @PostMapping
    @ApiOperation(value = "Create a new Student")
    public StudentResponseDto create(@RequestBody StudentRequestDto requestDto) {
        Student student = studentService.save(studentMapper.mapToModel(requestDto));
        return studentMapper.mapToDto(student);
    }

    @GetMapping("/name")
    @ApiOperation(value = "Get student by firstName and lastName")
    public StudentResponseDto getByFirstNameAndLastName(@RequestParam String firstName,
                                                        @RequestParam String lastName) {
        Student student = studentService.getByFirstNameAndLastName(firstName, lastName);
        return studentMapper.mapToDto(student);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete student by id")
    void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update student by id")
    public StudentResponseDto updateById(@PathVariable Long id,
                                         @RequestBody StudentRequestDto requestDto) {
        Student student = studentMapper.mapToModel(requestDto);
        student.setId(id);
        return studentMapper.mapToDto(studentService.save(student));
    }

    @GetMapping
    @ApiOperation(value = "Find all students with pagination and sort")
    public List<StudentResponseDto> getAll(@RequestParam(defaultValue = "20") Integer count,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "firstName") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count, sortUtil.getSort(sortBy));
        return studentService.getAll(pageRequest)
                .stream()
                .map(studentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/teacher")
    @ApiOperation(value = "Find all student by teacherId with pagination and sort")
    public List<StudentResponseDto> getAllByTeacherId(
            @RequestParam(defaultValue = "20") Integer count,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam Long teacherId) {
        PageRequest pageRequest = PageRequest.of(page, count, sortUtil.getSort(sortBy));
        return studentService.getAllByTeacherId(teacherId, pageRequest)
                .stream()
                .map(studentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/attach/teacher")
    @ApiOperation(value = "Attach teacher to student")
    void attachTeacherToStudent(@PathVariable Long id,
                                @RequestParam Long teacherId) {
        studentService.attachTeacherToStudent(id, teacherId);
    }
}
