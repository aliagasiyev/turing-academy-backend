package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "advantage")
@Data
public class AdvantageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20, message = "Title cannot exceed 20 characters")
    private String title;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;
}
