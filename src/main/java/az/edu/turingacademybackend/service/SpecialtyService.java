package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.SpecialtyRequestDto;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpecialtyService {

    List<SpecialtyResponseDto> getAllSpecialties();

    SpecialtyResponseDto getSpecialtyById(Long id);

    SpecialtyResponseDto create(SpecialtyRequestDto dto,
                                MultipartFile coverPhoto,
                                MultipartFile syllabus,
                                List<MultipartFile> teacherPhotos,
                                List<MultipartFile> graduatePhotos);

    SpecialtyResponseDto update(Long id,
                                SpecialtyRequestDto dto,
                                MultipartFile coverPhoto,
                                MultipartFile syllabus,
                                List<MultipartFile> teacherPhotos,
                                List<MultipartFile> graduatePhotos);

    SpecialtyResponseDto createFromJson(String dataJson,
                                        MultipartFile coverPhoto,
                                        MultipartFile syllabus,
                                        List<MultipartFile> teacherPhotos,
                                        List<MultipartFile> graduatePhotos);

    SpecialtyResponseDto updateFromJson(Long id,
                                        String dataJson,
                                        MultipartFile coverPhoto,
                                        MultipartFile syllabus,
                                        List<MultipartFile> teacherPhotos,
                                        List<MultipartFile> graduatePhotos);

    String deleteSpecialty(Long id);
}