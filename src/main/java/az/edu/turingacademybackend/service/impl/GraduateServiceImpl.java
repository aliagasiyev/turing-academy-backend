package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.GraduateRequest;
import az.edu.turingacademybackend.dto.response.GraduateResponse;
import az.edu.turingacademybackend.entity.GraduateEntity;
import az.edu.turingacademybackend.exceptions.DuplicateResourceException;
import az.edu.turingacademybackend.exceptions.InvalidRequestException;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.GraduateMapper;
import az.edu.turingacademybackend.repository.GraduateRepository;
import az.edu.turingacademybackend.service.GraduateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraduateServiceImpl implements GraduateService {

    private final GraduateRepository repository;
    private final GraduateMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public GraduateResponse createGraduate(String graduateJson, MultipartFile coverPhoto) {
        try {
            GraduateRequest request = objectMapper.readValue(graduateJson, GraduateRequest.class);

            if (repository.existsByStudentName(request.getStudentName())) {
                throw new DuplicateResourceException("Graduate with name " + request.getStudentName() + " already exists.");
            }

            if (coverPhoto != null && !coverPhoto.isEmpty()) {
                String photoUrl = saveCoverPhoto(coverPhoto);
                request.setCoverPhoto(photoUrl);
            }

            GraduateEntity graduate = mapper.toEntity(request);
            GraduateEntity saved = repository.save(graduate);
            return mapper.toDto(saved);

        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Invalid JSON format for GraduateRequest");
        }
    }

    @Override
    public GraduateResponse updateGraduate(Long id, String graduateJson, MultipartFile coverPhoto) {
        try {
            GraduateEntity existingGraduate = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Graduate not found with id: " + id));

            GraduateRequest request = objectMapper.readValue(graduateJson, GraduateRequest.class);

            if (coverPhoto != null && !coverPhoto.isEmpty()) {
                String photoUrl = saveCoverPhoto(coverPhoto);
                request.setCoverPhoto(photoUrl);
            } else {
                request.setCoverPhoto(existingGraduate.getCoverPhoto());
            }

            mapper.updateGraduateFromRequest(request, existingGraduate);
            GraduateEntity updated = repository.save(existingGraduate);
            return mapper.toDto(updated);

        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Invalid JSON format for GraduateRequest");
        }
    }

    @Override
    public void delete(Long id) {
        GraduateEntity graduate = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Graduate not found with id: " + id));
        repository.delete(graduate);
    }

    @Override
    public GraduateResponse getById(Long id) {
        GraduateEntity graduate = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Graduate not found with id: " + id));
        return mapper.toDto(graduate);
    }

    @Override
    public List<GraduateResponse> getAll(Integer limit) {
        List<GraduateEntity> graduates = repository.findAll();
        if (limit != null && limit > 0 && limit < graduates.size()) {
            graduates = graduates.subList(0, limit);
        }
        return mapper.toDtoList(graduates);
    }

    private String saveCoverPhoto(MultipartFile coverPhoto) {
        try {
            String basePath = System.getProperty("user.dir");
            String uploadDir = basePath + "/uploads/graduates";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + coverPhoto.getOriginalFilename();
            String filePath = uploadDir + "/" + fileName;
            coverPhoto.transferTo(new File(filePath));

            return "/uploads/graduates/" + fileName;
        } catch (Exception ex) {
            throw new RuntimeException("Cover photo upload failed: " + ex.getMessage());
        }
    }
}
