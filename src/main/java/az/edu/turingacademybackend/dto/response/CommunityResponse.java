package az.edu.turingacademybackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class CommunityResponse {

    private List<String> topSliderPhotos;
    private String environmentBannerText;

    private List<CommunityCardResponse> cards;
    private List<AdvantageResponse> advantages;
    private GalleryResponse gallery;

    private String communityVideoUrl;
    private String bottomMessage;
}
