package az.edu.turingacademybackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraduateResponse {

    private Long id;

    private String studentName;

    private String category;

    private String desc;

    private String route;

    private String coverPhoto;

    private String videoUrl;
}
