package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryRequest {

    @NotBlank
    private String galleryTitle;

    @NotBlank
    private String galleryDescription;

    @NotNull
    @Size(max = 12, message = "Exactly 12 gallery photos are required")
    private List<@NotBlank String> galleryPhotos;
}
