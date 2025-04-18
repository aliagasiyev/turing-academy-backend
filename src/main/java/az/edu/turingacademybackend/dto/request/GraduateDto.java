package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduateDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Position and company is required")
    private String positionWithCompany;

    @NotBlank(message = "Cover photo URL is required")
    @URL(message = "Cover photo URL must be valid")
    private String coverPhotoUrl;

    @NotBlank(message = "Video URL is required")
    @URL(message = "Video URL must be valid")
    private String videoUrl;
}
