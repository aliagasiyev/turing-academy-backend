package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import az.edu.turingacademybackend.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schoolarship")
@RequiredArgsConstructor
@Validated
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping
    public ResponseEntity<List<ScholarshipResponseDto>> getAll() {
        return ResponseEntity.ok(scholarshipService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scholarshipService.getById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ScholarshipResponseDto> create(
            @RequestPart("data") String dataJson,
            @RequestPart("coverPhoto") MultipartFile file) {
        return new ResponseEntity<>(scholarshipService.createFromJson(dataJson, file), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ScholarshipResponseDto> update(
            @PathVariable Long id,
            @RequestPart("data") String dataJson,
            @RequestPart(value = "coverPhoto", required = false) MultipartFile file) {
        return ResponseEntity.ok(scholarshipService.updateFromJson(id, dataJson, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        String message = scholarshipService.delete(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}