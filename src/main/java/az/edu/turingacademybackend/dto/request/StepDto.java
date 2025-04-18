package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StepDto {

    @NotNull(message = "Step number cannot be null")
    @Min(value = 1, message = "Step number must be at least 1")
    private Integer stepNumber;

    @NotBlank(message = "Step title cannot be empty")
    @Size(max = 255, message = "Step title can be at most 255 characters")
    private String title;

    @NotBlank(message = "Step description cannot be empty")
    private String description;
}
