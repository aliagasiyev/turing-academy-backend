package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "graduates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String positionWithCompany;
    private String coverPhotoUrl;
    private String videoUrl;
}
