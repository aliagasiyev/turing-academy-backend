package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScholarshipService {

    List<ScholarshipResponseDto> getAll();

    ScholarshipResponseDto getById(Long id);

    ScholarshipResponseDto create(ScholarshipRequestDto dto, MultipartFile file);

    ScholarshipResponseDto update(Long id, ScholarshipRequestDto dto, MultipartFile file);

    ScholarshipResponseDto createFromJson(String dataJson, MultipartFile file);

    ScholarshipResponseDto updateFromJson(Long id, String dataJson, MultipartFile file);

    String delete(Long id);
}
