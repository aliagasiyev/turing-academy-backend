package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "home_page_content")
@Data
@NoArgsConstructor
public class HomePageContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header_video_url")
    private String headerVideoUrl;

    private String motto;

    @Column(name = "motto_description")
    private String mottoDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "home_page_id")
    private List<PartnerEntity> partners;

}
