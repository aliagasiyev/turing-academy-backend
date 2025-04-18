package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "gallery")
@Data
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String galleryTitle;

    private String galleryDescription;

    @ElementCollection
    @CollectionTable(name = "gallery_photos", joinColumns = @JoinColumn(name = "gallery_id"))
    @Column(name = "photo_url")
    @Size( max = 12, message = "Exactly 12 gallery photos are required")
    private List<String> galleryPhotos;
}
