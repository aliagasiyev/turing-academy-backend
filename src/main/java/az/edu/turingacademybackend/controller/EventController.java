package az.edu.turingacademybackend.controller;

import az.edu.turingacademybackend.dto.response.EventResponseDTO;
import az.edu.turingacademybackend.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAll(@RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(eventService.getAllEvents(limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventResponseDTO> create(
            @RequestPart("event") String eventJson,
            @RequestPart("photos") List<MultipartFile> photos,
            @RequestPart("guestPhotos") List<MultipartFile> guestPhotos
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.createEventFromJson(eventJson, photos, guestPhotos));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventResponseDTO> update(
            @PathVariable Long id,
            @RequestPart("event") String eventJson,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos
    ) {
        return ResponseEntity.ok(eventService.updateEventFromJson(id, eventJson, photos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        String message = eventService.deleteEvent(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}