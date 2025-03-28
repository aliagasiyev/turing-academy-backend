package az.edu.turingacademybackend.entity;

import az.edu.turingacademybackend.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime eventDate;

    private LocalDateTime registerDeadline;

    private BigDecimal price;

    private Integer slots;

    private String registerLink;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ElementCollection
    private List<String> photos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private List<GuestEntity> guests;
}
