package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.GraduateRequest;
import az.edu.turingacademybackend.dto.response.GraduateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GraduateService {

    GraduateResponse createGraduate(String graduateJson, MultipartFile coverPhoto);

    GraduateResponse updateGraduate(Long id, String graduateJson, MultipartFile coverPhoto);

    void delete(Long id);

    GraduateResponse getById(Long id);

    List<GraduateResponse> getAll(Integer limit);
}
