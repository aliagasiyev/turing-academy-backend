package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import az.edu.turingacademybackend.entity.ScholarshipEntity;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.ScholarshipMapper;
import az.edu.turingacademybackend.repository.ScholarshipRepository;
import az.edu.turingacademybackend.service.ScholarshipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepository repository;
    private final ScholarshipMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<ScholarshipResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScholarshipResponseDto getById(Long id) {
        ScholarshipEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scholarship not found with id: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public ScholarshipResponseDto create(ScholarshipRequestDto dto, MultipartFile file) {
        try {
            String fileName = uploadFile(file);
            dto.setCoverPhotoUrl(fileName);
            ScholarshipEntity entity = mapper.toEntity(dto);
            ScholarshipEntity saved = repository.save(entity);
            return mapper.toResponseDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to save scholarship: " + e.getRootCause().getMessage());
        }
    }

    @Override
    public ScholarshipResponseDto update(Long id, ScholarshipRequestDto dto, MultipartFile file) {
        ScholarshipEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scholarship not found with id: " + id));

        try {
            if (file != null && !file.isEmpty()) {
                String fileName = uploadFile(file);
                dto.setCoverPhotoUrl(fileName);
            } else {
                dto.setCoverPhotoUrl(existing.getCoverPhotoUrl());
            }

            ScholarshipEntity updated = mapper.toEntity(dto);
            updated.setId(existing.getId());
            ScholarshipEntity saved = repository.save(updated);
            return mapper.toResponseDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to update scholarship: " + e.getRootCause().getMessage());
        }
    }

    @Override
    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Scholarship not found with id: " + id);
        }

        try {
            repository.deleteById(id);
            return "Deleted successfully with id: " + id;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to delete scholarship: " + e.getRootCause().getMessage());
        }
    }


    @Override
    public ScholarshipResponseDto createFromJson (String dataJson, MultipartFile file){
        try {
            ScholarshipRequestDto dto = objectMapper.readValue(dataJson, ScholarshipRequestDto.class);
            return create(dto, file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for ScholarshipRequestDto", e);
        }
    }

    @Override
    public ScholarshipResponseDto updateFromJson (Long id, String dataJson, MultipartFile file){
        try {
            ScholarshipRequestDto dto = objectMapper.readValue(dataJson, ScholarshipRequestDto.class);
            return update(id, dto, file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for ScholarshipRequestDto", e);
        }
    }

    private String uploadFile (MultipartFile file){
        try {
            String uploadDir = "uploads/scholarships/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/scholarships/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}