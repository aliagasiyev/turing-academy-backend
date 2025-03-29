package az.edu.turingacademybackend.dto.response;

import az.edu.turingacademybackend.dto.request.FaqItemDto;
import az.edu.turingacademybackend.dto.request.StepDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScholarshipResponseDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private String coverPhotoUrl;
    private Integer slotsLeft;
    private LocalDate registerDeadline;
    private String applyLink;
    private List<StepDto> steps;
    private List<FaqItemDto> faq;
}
