package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.request.CommunityRequest;
import az.edu.turingacademybackend.dto.response.CommunityResponse;
import az.edu.turingacademybackend.service.CommunityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<CommunityResponse> getCommunity() {
        return ResponseEntity.ok(communityService.getCommunity());
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommunityResponse> updateCommunity(
            @RequestPart("data") String data,
            @RequestPart("sliderPhotos") List<MultipartFile> sliderPhotos,
            @RequestPart("galleryPhotos") List<MultipartFile> galleryPhotos,
            @RequestPart(value = "video", required = false) MultipartFile video
    ) throws JsonProcessingException {
        CommunityRequest request = objectMapper.readValue(data, CommunityRequest.class);
        return ResponseEntity.ok(communityService.updateCommunity(request, sliderPhotos, galleryPhotos, video));
    }

}
