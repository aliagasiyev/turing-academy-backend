package az.edu.turingacademybackend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScholarshipRequestDto {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Title can be at most 255 characters")
    private String title;

    @NotBlank(message = "Subtitle cannot be empty")
    @Size(max = 255, message = "Subtitle can be at most 255 characters")
    private String subtitle;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Cover photo URL cannot be empty")
    @Pattern(regexp = "^(http|https)://.*$", message = "Must be a valid URL")
    private String coverPhotoUrl;

    @NotNull(message = "Slots left cannot be null")
    @Min(value = 0, message = "Slots left cannot be negative")
    private Integer slotsLeft;

    @NotNull(message = "Register deadline cannot be null")
    @Future(message = "Register deadline must be in the future")
    private LocalDate registerDeadline;

    @NotBlank(message = "Apply link cannot be empty")
    @Pattern(regexp = "^(http|https)://.*$", message = "Must be a valid URL")
    private String applyLink;

    @NotEmpty(message = "At least one step is required")
    private List<@Valid StepDto> steps;

    @NotEmpty(message = "At least one FAQ item is required")
    private List<@Valid FaqItemDto> faq;
}
