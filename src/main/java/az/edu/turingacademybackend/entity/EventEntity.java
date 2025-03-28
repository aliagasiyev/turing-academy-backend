package az.edu.turingacademybackend.entity;

import az.edu.turingacademybackend.enums.EventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    @Column(columnDefinition = "TEXT")
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

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ElementCollection
    @Size(min = 1)
    private List<@URL String> photos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id") // Guest cədvəlində foreign key
    private List<GuestEntity> guests;
}
