package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "guests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String positionWithCompany;

    private String photoUrl;

    private String linkedinUrl;
}
