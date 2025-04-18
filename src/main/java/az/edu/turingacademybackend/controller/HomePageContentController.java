package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.request.HomePageContentRequest;
import az.edu.turingacademybackend.dto.response.HomePageContentResponse;
import az.edu.turingacademybackend.service.HomePageContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomePageContentController {

    private final HomePageContentService homePageContentService;

    @GetMapping
    public ResponseEntity<HomePageContentResponse> getHomePageContent() {
        HomePageContentResponse response = homePageContentService.getHomePageContent();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<HomePageContentResponse> updateHomePageContent(@RequestBody HomePageContentRequest request) {
        HomePageContentResponse response = homePageContentService.updateHomePageContent(request);
        return ResponseEntity.ok(response);
    }
}
