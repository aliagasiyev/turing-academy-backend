package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.request.SpecialtyRequestDto;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import az.edu.turingacademybackend.service.SpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialty")
@RequiredArgsConstructor
@Validated
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

    @PostMapping
    public ResponseEntity<SpecialtyResponseDto> createSpecialty(
            @Valid @RequestBody SpecialtyRequestDto requestDto) {
        SpecialtyResponseDto response = specialtyService.createSpecialty(requestDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyResponseDto> updateSpecialty(
            @PathVariable Long id,
            @Valid @RequestBody SpecialtyRequestDto requestDto) {
        SpecialtyResponseDto response = specialtyService.updateSpecialty(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }
}
