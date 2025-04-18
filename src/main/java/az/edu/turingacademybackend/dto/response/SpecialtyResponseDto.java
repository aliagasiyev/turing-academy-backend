package az.edu.turingacademybackend.dto.response;

import az.edu.turingacademybackend.dto.request.FaqDto;
import az.edu.turingacademybackend.dto.request.GraduateDto;
import az.edu.turingacademybackend.dto.request.ModuleDto;
import az.edu.turingacademybackend.dto.request.TeacherDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyResponseDto {

    private Long id;

    private String title;
    private String subtitle;
    private String description;

    private String coverPhotoUrl;
    private String youtubeVideoUrl;
    private String syllabusUrl;
    private String applyLink;

    private List<ModuleDto> modules;
    private List<TeacherDto> teachers;
    private List<GraduateDto> graduates;
    private FaqDto faq;
}
