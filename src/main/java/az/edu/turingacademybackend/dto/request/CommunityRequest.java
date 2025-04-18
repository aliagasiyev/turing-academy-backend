package az.edu.turingacademybackend.dto.request;

import jakarta.validation.Valid;
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
public class CommunityRequest {

    @NotNull
    @Size(max = 7, message = "Exactly 7 slider photos are required")
    private List<@NotBlank String> topSliderPhotos;

    @NotBlank
    private String environmentBannerText;

    @NotNull
    @Size( max = 4, message = "Exactly 4 community cards are required")
    @Valid
    private List<CommunityCardRequest> cards;

    @NotNull
    @Size(max = 8, message = "Exactly 8 advantages are required")
    @Valid
    private List<AdvantageRequest> advantages;

    @NotNull
    @Valid
    private GalleryRequest gallery;

    @NotBlank
    private String communityVideoUrl;

    @NotBlank
    private String bottomMessage;
}
