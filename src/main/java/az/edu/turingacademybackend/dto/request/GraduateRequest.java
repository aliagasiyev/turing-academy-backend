package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraduateRequest {

    @NotBlank(message = "Student name cannot be blank")
    private String studentName;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotBlank(message = "Description cannot be blank")
    private String desc;

    @NotBlank(message = "Route cannot be blank")
    private String route;

    @NotBlank(message = "Cover photo URL cannot be blank")
    @URL(message = "Cover photo must be a valid URL")
    private String coverPhoto;

    @NotBlank(message = "Video URL cannot be blank")
    @URL(message = "Video URL must be a valid URL")
    private String videoUrl;
}
