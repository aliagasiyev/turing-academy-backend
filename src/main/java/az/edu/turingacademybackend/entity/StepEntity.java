package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "scholarship_steps")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stepNumber;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
}
