package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import az.edu.turingacademybackend.service.ScholarshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schoolarship")
@RequiredArgsConstructor
@Validated
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping
    public ResponseEntity<List<ScholarshipResponseDto>> getAll() {
        List<ScholarshipResponseDto> list = scholarshipService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scholarshipService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ScholarshipResponseDto> create(@Valid @RequestBody ScholarshipRequestDto dto) {
        ScholarshipResponseDto created = scholarshipService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScholarshipResponseDto> update(@PathVariable Long id,
                                                         @Valid @RequestBody ScholarshipRequestDto dto) {
        ScholarshipResponseDto updated = scholarshipService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        scholarshipService.delete(id);
    }
}
