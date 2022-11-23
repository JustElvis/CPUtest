package dto.request;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Min(value = 18)
    private int age;
    @Email
    private String email;
    private String speciality;
    private List<Long> teachersIds;
}
