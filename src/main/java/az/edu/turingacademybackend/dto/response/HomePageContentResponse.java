package az.edu.turingacademybackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class HomePageContentResponse {

    private String headerVideoUrl;
    private String motto;
    private String mottoDescription;
    private List<PartnerResponse> partners;

}
