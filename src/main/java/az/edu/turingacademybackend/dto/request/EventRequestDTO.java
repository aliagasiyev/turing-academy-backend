package az.edu.turingacademybackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime eventDate;

    @NotNull
    private LocalDateTime registerDeadline;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @NotNull
    @Min(1)
    private Integer slots;

    @NotBlank
    @URL
    private String registerLink;

    @Size(min = 1)
    private List<@URL String> photos;

    @Size(min = 1)
    private List<GuestDTO> guests;
}
