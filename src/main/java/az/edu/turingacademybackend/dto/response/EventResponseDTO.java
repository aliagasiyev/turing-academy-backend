package az.edu.turingacademybackend.dto.response;

import az.edu.turingacademybackend.dto.request.GuestDTO;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long id;

    private String name;
    private String category;
    private String description;

    private LocalDateTime eventDate;
    private LocalDateTime registerDeadline;
    private BigDecimal price;
    private Integer slots;
    private String registerLink;

    private String status;

    private List<String> photos;

    private List<GuestDTO> guests;
}
