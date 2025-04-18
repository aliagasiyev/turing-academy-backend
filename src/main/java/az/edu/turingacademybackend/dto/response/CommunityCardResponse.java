package az.edu.turingacademybackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityCardResponse {

    private String photoUrl;
    private String description;
    private String name;
    private String subDescription;
}

