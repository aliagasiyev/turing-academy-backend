package az.edu.turingacademybackend.service;


import az.edu.turingacademybackend.dto.request.SpecialtyRequestDto;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;

import java.util.List;

public interface SpecialtyService {

    List<SpecialtyResponseDto> getAllSpecialties();

    SpecialtyResponseDto getSpecialtyById(Long id);

    SpecialtyResponseDto createSpecialty(SpecialtyRequestDto requestDto);

    SpecialtyResponseDto updateSpecialty(Long id, SpecialtyRequestDto requestDto);

    void deleteSpecialty(Long id);
}
