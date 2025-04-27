package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.response.GraduateResponse;
import az.edu.turingacademybackend.service.GraduateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v1/graduates")
@RequiredArgsConstructor
public class GraduateController {

    private final GraduateService graduateService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<GraduateResponse> createGraduate(
            @RequestPart("graduate") String graduateJson,
            @RequestPart(value = "coverPhoto", required = false) MultipartFile coverPhoto) {

        GraduateResponse response = graduateService.createGraduate(graduateJson, coverPhoto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<GraduateResponse> updateGraduate(
            @PathVariable Long id,
            @RequestPart("graduate") String graduateJson,
            @RequestPart(value = "coverPhoto", required = false) MultipartFile coverPhoto) {

        GraduateResponse response = graduateService.updateGraduate(id, graduateJson, coverPhoto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GraduateResponse>> getAllGraduates(
            @RequestParam(required = false) Integer limit) {

        List<GraduateResponse> graduates = graduateService.getAll(limit);
        return ResponseEntity.ok(graduates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GraduateResponse> getGraduateById(@PathVariable Long id) {
        GraduateResponse response = graduateService.getById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGraduate(@PathVariable Long id) {
        graduateService.delete(id);
        return ResponseEntity.ok("Graduate deleted successfully");
    }
}
