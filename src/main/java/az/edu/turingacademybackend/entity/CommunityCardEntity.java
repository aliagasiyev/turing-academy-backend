package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "community_card")
@Data
public class CommunityCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;

    @Column(length = 500)
    private String description;

    private String name;

    private String subDescription;
}
