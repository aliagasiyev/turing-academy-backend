package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import az.edu.turingacademybackend.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping
    public ResponseEntity<List<SpecialtyResponseDto>> getAllSpecialties() {
        List<SpecialtyResponseDto> specialties = specialtyService.getAllSpecialties();
        return ResponseEntity.ok(specialties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponseDto> getSpecialtyById(@PathVariable Long id) {
        SpecialtyResponseDto response = specialtyService.getSpecialtyById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SpecialtyResponseDto> createSpecialty(
            @RequestPart("data") String dataJson,
            @RequestPart("coverPhoto") MultipartFile coverPhoto,
            @RequestPart("syllabus") MultipartFile syllabus,
            @RequestPart("teacherPhotos") List<MultipartFile> teacherPhotos,
            @RequestPart("graduatePhotos") List<MultipartFile> graduatePhotos) {
        return new ResponseEntity<>(
                specialtyService.createFromJson(dataJson, coverPhoto, syllabus, teacherPhotos, graduatePhotos),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SpecialtyResponseDto> updateSpecialty(
            @PathVariable Long id,
            @RequestPart("data") String dataJson,
            @RequestPart(value = "coverPhoto", required = false) MultipartFile coverPhoto,
            @RequestPart(value = "syllabus", required = false) MultipartFile syllabus,
            @RequestPart(value = "teacherPhotos", required = false) List<MultipartFile> teacherPhotos,
            @RequestPart(value = "graduatePhotos", required = false) List<MultipartFile> graduatePhotos) {
        return ResponseEntity.ok(
                specialtyService.updateFromJson(id, dataJson, coverPhoto, syllabus, teacherPhotos, graduatePhotos)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSpecialty(@PathVariable Long id) {
        String message = specialtyService.deleteSpecialty(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}