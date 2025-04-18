package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    private String positionWithCompany;

    @NotBlank
    @URL
    private String photoUrl;

    @NotBlank
    @URL
    private String linkedinUrl;
}
