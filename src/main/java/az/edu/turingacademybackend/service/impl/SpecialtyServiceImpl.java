package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.SpecialtyRequestDto;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import az.edu.turingacademybackend.entity.SpecialtyEntity;
import az.edu.turingacademybackend.exceptions.*;
import az.edu.turingacademybackend.mapper.SpecialtyMapper;
import az.edu.turingacademybackend.repository.SpecialtyRepository;
import az.edu.turingacademybackend.service.SpecialtyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<SpecialtyResponseDto> getAllSpecialties() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyResponseDto getSpecialtyById(Long id) {
        SpecialtyEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public SpecialtyResponseDto create(SpecialtyRequestDto dto, MultipartFile coverPhoto, MultipartFile syllabus,
                                       List<MultipartFile> teacherPhotos, List<MultipartFile> graduatePhotos) {
        validate(dto);

        dto.setCoverPhotoUrl(saveFile(coverPhoto, "specialties/cover/"));
        dto.setSyllabusUrl(saveFile(syllabus, "specialties/syllabus/"));

        IntStream.range(0, dto.getTeachers().size()).forEach(i ->
                dto.getTeachers().get(i).setPhotoUrl(saveFile(teacherPhotos.get(i), "specialties/teachers/")));

        IntStream.range(0, dto.getGraduates().size()).forEach(i ->
                dto.getGraduates().get(i).setCoverPhotoUrl(saveFile(graduatePhotos.get(i), "specialties/graduates/")));

        SpecialtyEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public SpecialtyResponseDto update(Long id, SpecialtyRequestDto dto, MultipartFile coverPhoto, MultipartFile syllabus,
                                       List<MultipartFile> teacherPhotos, List<MultipartFile> graduatePhotos) {
        SpecialtyEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));

        validateFaq(dto);

        dto.setCoverPhotoUrl((coverPhoto != null && !coverPhoto.isEmpty())
                ? saveFile(coverPhoto, "specialties/cover/")
                : existing.getCoverPhotoUrl());

        dto.setSyllabusUrl((syllabus != null && !syllabus.isEmpty())
                ? saveFile(syllabus, "specialties/syllabus/")
                : existing.getSyllabusUrl());

        IntStream.range(0, dto.getTeachers().size()).forEach(i -> {
            if (teacherPhotos != null && i < teacherPhotos.size())
                dto.getTeachers().get(i).setPhotoUrl(saveFile(teacherPhotos.get(i), "specialties/teachers/"));
            else
                dto.getTeachers().get(i).setPhotoUrl(existing.getTeachers().get(i).getPhotoUrl());
        });

        IntStream.range(0, dto.getGraduates().size()).forEach(i -> {
            if (graduatePhotos != null && i < graduatePhotos.size())
                dto.getGraduates().get(i).setCoverPhotoUrl(saveFile(graduatePhotos.get(i), "specialties/graduates/"));
            else
                dto.getGraduates().get(i).setCoverPhotoUrl(existing.getGraduates().get(i).getCoverPhotoUrl());
        });

        SpecialtyEntity updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    @Override
    public SpecialtyResponseDto createFromJson(String dataJson, MultipartFile coverPhoto, MultipartFile syllabus,
                                               List<MultipartFile> teacherPhotos, List<MultipartFile> graduatePhotos) {
        try {
            SpecialtyRequestDto dto = objectMapper.readValue(dataJson, SpecialtyRequestDto.class);
            return create(dto, coverPhoto, syllabus, teacherPhotos, graduatePhotos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for SpecialtyRequestDto", e);
        }
    }

    @Override
    public SpecialtyResponseDto updateFromJson(Long id, String dataJson, MultipartFile coverPhoto, MultipartFile syllabus,
                                               List<MultipartFile> teacherPhotos, List<MultipartFile> graduatePhotos) {
        try {
            SpecialtyRequestDto dto = objectMapper.readValue(dataJson, SpecialtyRequestDto.class);
            return update(id, dto, coverPhoto, syllabus, teacherPhotos, graduatePhotos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for SpecialtyRequestDto", e);
        }
    }

    @Override
    public String deleteSpecialty(Long id) {
        SpecialtyEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));

        if (existing.getModules() != null && !existing.getModules().isEmpty()) {
            throw new OperationNotAllowedException("Cannot delete specialty with existing modules");
        }

        repository.delete(existing);
        return "Deleted successfully with id: " + id;
    }

    private void validate(SpecialtyRequestDto dto) {
        if (repository.existsByTitleIgnoreCase(dto.getTitle())) {
            throw new DuplicateResourceException("A specialty with this title already exists");
        }
        validateFaq(dto);
    }

    private void validateFaq(SpecialtyRequestDto dto) {
        if (dto.getFaq().getQuestions().size() != dto.getFaq().getAnswers().size()) {
            throw new InvalidRequestException("FAQ questions and answers must have the same length");
        }
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