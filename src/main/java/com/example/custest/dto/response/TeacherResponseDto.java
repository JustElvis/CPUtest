package com.example.custest.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String subject;
    private String email;
    private List<Long> studentsIds;
}
