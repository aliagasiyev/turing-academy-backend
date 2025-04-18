package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.CommunityRequest;
import az.edu.turingacademybackend.dto.response.CommunityResponse;
import az.edu.turingacademybackend.entity.CommunityCardEntity;
import az.edu.turingacademybackend.entity.CommunityContentEntity;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.CommunityMapper;
import az.edu.turingacademybackend.repository.CommunityRepository;
import az.edu.turingacademybackend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository repository;
    private final CommunityMapper mapper;

    @Override
    public CommunityResponse getCommunity() {
        CommunityContentEntity entity = repository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new ResourceNotFoundException("Community content not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public CommunityResponse updateCommunity(
            CommunityRequest request,
            List<MultipartFile> sliderPhotos,
            List<MultipartFile> galleryPhotos,
            MultipartFile video
    ) {
        CommunityContentEntity existing = repository.findFirstByOrderByIdAsc().orElse(null);

        if (sliderPhotos != null && !sliderPhotos.isEmpty()) {
            List<String> sliderUrls = sliderPhotos.stream()
                    .map(file -> saveFile(file, "community/slider/"))
                    .collect(Collectors.toList());
            request.setTopSliderPhotos(sliderUrls);
        }

        if (galleryPhotos != null && !galleryPhotos.isEmpty() && request.getGallery() != null) {
            List<String> galleryUrls = galleryPhotos.stream()
                    .map(file -> saveFile(file, "community/gallery/"))
                    .collect(Collectors.toList());
            request.getGallery().setGalleryPhotos(galleryUrls);
        }

        // Video faylını yadda saxla
        if (video != null && !video.isEmpty()) {
            String videoUrl = saveFile(video, "community/video/");
            request.setCommunityVideoUrl(videoUrl);
        }

        // Request-dən Entity yarat
        CommunityContentEntity entity = mapper.toEntity(request);

        // PhotoUrl boş olan kartlara placeholder ver
        if (entity.getCards() != null) {
            for (CommunityCardEntity card : entity.getCards()) {
                if (card.getPhotoUrl() == null || card.getPhotoUrl().isBlank()) {
                    card.setPhotoUrl("/uploads/placeholder.jpg");
                }
            }
        }

        // Əgər mövcud entity varsa, ID-ni saxla (update üçün)
        if (existing != null) {
            entity.setId(existing.getId());
        }

        // DB-yə yaz və cavabı geri qaytar
        return mapper.toResponse(repository.save(entity));
    }


    private String saveFile(MultipartFile file, String folderPath) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/" + folderPath;
            Files.createDirectories(Path.of(uploadDir));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Path.of(uploadDir + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + folderPath + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}