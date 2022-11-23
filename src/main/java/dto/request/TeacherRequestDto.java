package dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Min(value = 18)
    private int age;
    private String subject;
    @Email
    private String email;
}
