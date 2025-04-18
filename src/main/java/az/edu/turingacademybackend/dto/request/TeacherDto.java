package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Position and company is required")
    private String positionWithCompany;

    @NotBlank(message = "LinkedIn URL is required")
    @URL(message = "LinkedIn URL must be valid")
    private String linkedinUrl;

    @NotBlank(message = "Photo URL is required")
    @URL(message = "Photo URL must be valid")
    private String photoUrl;
}
