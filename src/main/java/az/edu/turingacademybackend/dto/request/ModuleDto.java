package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleDto {

    @NotBlank(message = "Module title is required")
    private String title;

    @NotBlank(message = "Module description is required")
    private String description;
}
