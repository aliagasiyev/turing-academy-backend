package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDto {

    @NotEmpty(message = "FAQ questions must not be empty")
    private List<@NotBlank(message = "Question must not be blank") String> questions;

    @NotEmpty(message = "FAQ answers must not be empty")
    private List<@NotBlank(message = "Answer must not be blank") String> answers;
}
