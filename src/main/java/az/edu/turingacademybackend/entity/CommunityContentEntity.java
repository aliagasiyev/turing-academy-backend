package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "community_content")
public class CommunityContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "community_slider_photos", joinColumns = @JoinColumn(name = "community_id"))
    @Column(name = "photo_url")
    @Size( max = 7, message = "Exactly 7 slider photos are required")
    private List<String> topSliderPhotos;

    private String environmentBannerText;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "community_id")
    @Size(max = 4, message = "Exactly 4 community cards are required")
    private List<CommunityCardEntity> cards;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "community_id")
    @Size(max = 8, message = "Exactly 8 advantages are required")
    private List<AdvantageEntity> advantages;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gallery_id")
    private GalleryEntity gallery;

    private String communityVideoUrl;

    private String bottomMessage;
}