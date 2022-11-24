package com.example.custest.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String speciality;
    private List<Long> teachersIds;
}
