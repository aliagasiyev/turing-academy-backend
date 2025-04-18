package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FaqItemDto {

    @NotBlank(message = "Question cannot be empty")
    @Size(max = 255, message = "Question can be at most 255 characters")
    private String question;

    @NotBlank(message = "Answer cannot be empty")
    private String answer;
}
