package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.SpecialtyRequestDto;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import az.edu.turingacademybackend.entity.SpecialtyEntity;
import az.edu.turingacademybackend.exceptions.*;
import az.edu.turingacademybackend.mapper.SpecialtyMapper;
import az.edu.turingacademybackend.repository.SpecialtyRepository;
import az.edu.turingacademybackend.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public List<SpecialtyResponseDto> getAllSpecialties() {
        return specialtyRepository.findAll()
                .stream()
                .map(specialtyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyResponseDto getSpecialtyById(Long id) {
        SpecialtyEntity specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));
        return specialtyMapper.toDto(specialty);
    }

    @Override
    public SpecialtyResponseDto createSpecialty(SpecialtyRequestDto requestDto) {

        boolean exists = specialtyRepository.existsByTitleIgnoreCase(requestDto.getTitle());
        if (exists) {
            throw new DuplicateResourceException("A specialty with this title already exists");
        }

        if (requestDto.getFaq().getQuestions().size() != requestDto.getFaq().getAnswers().size()) {
            throw new InvalidRequestException("FAQ questions and answers must have the same length");
        }

        SpecialtyEntity specialty = specialtyMapper.toEntity(requestDto);
        SpecialtyEntity saved = specialtyRepository.save(specialty);
        return specialtyMapper.toDto(saved);
    }

    @Override
    public SpecialtyResponseDto updateSpecialty(Long id, SpecialtyRequestDto requestDto) {
        SpecialtyEntity existing = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));

        if (requestDto.getFaq().getQuestions().size() != requestDto.getFaq().getAnswers().size()) {
            throw new InvalidRequestException("FAQ questions and answers must have the same length");
        }

        SpecialtyEntity updated = specialtyMapper.toEntity(requestDto);
        updated.setId(existing.getId());

        SpecialtyEntity saved = specialtyRepository.save(updated);
        return specialtyMapper.toDto(saved);
    }

    @Override
    public void deleteSpecialty(Long id) {
        SpecialtyEntity existing = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));

        if (existing.getModules() != null && !existing.getModules().isEmpty()) {
            throw new OperationNotAllowedException("Cannot delete specialty with existing modules");
        }

        specialtyRepository.delete(existing);
    }
}
