package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityCardRequest {

    @NotBlank
    private String photoUrl;

    @NotBlank
    private String description;

    @NotBlank
    private String name;

    @NotBlank
    private String subDescription;
}
