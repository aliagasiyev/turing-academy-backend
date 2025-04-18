package az.edu.turingacademybackend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "specialties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String coverPhotoUrl;
    private String youtubeVideoUrl;
    private String syllabusUrl;
    private String applyLink;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private List<ModuleEntity> modules;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private List<TeacherEntity> teachers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private List<GraduateEntity> graduates;

    @Embedded
    private FaqEntity faq;
}
