package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.CommunityRequest;
import az.edu.turingacademybackend.dto.response.CommunityResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityService {

    CommunityResponse getCommunity();

    CommunityResponse updateCommunity(
            CommunityRequest request,
            List<MultipartFile> sliderPhotos,
            List<MultipartFile> galleryPhotos,
            MultipartFile video
    );
}
