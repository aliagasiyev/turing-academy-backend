package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvantageRequest {

    @NotBlank
    @Size(max = 20, message = "Title cannot exceed 20 characters")
    private String title;

    @NotBlank
    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;
}

