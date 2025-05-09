package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "graduates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraduateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private String category;

    @Column(length = 1000)
    private String desc;

    private String route;

    private String coverPhoto;

    private String videoUrl;

}
