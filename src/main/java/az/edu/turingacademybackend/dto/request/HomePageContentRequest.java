package az.edu.turingacademybackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class HomePageContentRequest {

    private String headerVideoUrl;
    private String motto;
    private String mottoDescription;
    private List<PartnerRequest> partners;

}
