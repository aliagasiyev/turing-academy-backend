package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Subtitle is required")
    private String subtitle;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Cover photo URL is required")
    @URL(message = "Cover photo URL must be valid")
    private String coverPhotoUrl;

    @NotBlank(message = "YouTube video URL is required")
    @URL(message = "YouTube video URL must be valid")
    private String youtubeVideoUrl;

    @NotBlank(message = "Syllabus URL is required")
    @URL(message = "Syllabus URL must be valid")
    private String syllabusUrl;

    @NotBlank(message = "Apply link is required")
    @URL(message = "Apply link must be valid")
    private String applyLink;

    @NotEmpty(message = "At least one module is required")
    private List<ModuleDto> modules;

    @NotEmpty(message = "At least one teacher is required")
    private List<TeacherDto> teachers;

    @NotEmpty(message = "At least one graduate is required")
    private List<GraduateDto> graduates;

    @NotNull(message = "FAQ section is required")
    private FaqDto faq;
}
