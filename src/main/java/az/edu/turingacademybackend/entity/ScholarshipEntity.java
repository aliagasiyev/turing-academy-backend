package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "scholarships")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String coverPhotoUrl;
    private Integer slotsLeft;

    private LocalDate registerDeadline;
    private String applyLink;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scholarship_id")
    private List<StepEntity> steps;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scholarship_id")
    private List<FaqItemEntity> faq;
}
