package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;

import java.util.List;

public interface ScholarshipService {

    List<ScholarshipResponseDto> getAll();

    ScholarshipResponseDto getById(Long id);

    ScholarshipResponseDto create(ScholarshipRequestDto dto);

    ScholarshipResponseDto update(Long id, ScholarshipRequestDto dto);

    void delete(Long id);
}
