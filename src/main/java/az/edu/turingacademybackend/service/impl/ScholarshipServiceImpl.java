package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import az.edu.turingacademybackend.entity.ScholarshipEntity;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.ScholarshipMapper;
import az.edu.turingacademybackend.repository.ScholarshipRepository;
import az.edu.turingacademybackend.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepository repository;
    private final ScholarshipMapper mapper;

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
    public ScholarshipResponseDto create(ScholarshipRequestDto dto) {
        try {
            ScholarshipEntity entity = mapper.toEntity(dto);
            ScholarshipEntity saved = repository.save(entity);
            return mapper.toResponseDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to save scholarship: " + e.getRootCause().getMessage());
        }
    }

    @Override
    public ScholarshipResponseDto update(Long id, ScholarshipRequestDto dto) {
        ScholarshipEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scholarship not found with id: " + id));

        try {
            ScholarshipEntity updated = mapper.toEntity(dto);
            updated.setId(existing.getId()); // keep the original ID
            ScholarshipEntity saved = repository.save(updated);
            return mapper.toResponseDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to update scholarship: " + e.getRootCause().getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Scholarship not found with id: " + id);
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to delete scholarship: " + e.getRootCause().getMessage());
        }
    }
}
