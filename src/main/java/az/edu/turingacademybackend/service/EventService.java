package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.EventRequestDTO;
import az.edu.turingacademybackend.dto.response.EventResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    List<EventResponseDTO> getAllEvents(Integer limit);

    EventResponseDTO getEventById(Long id);

    EventResponseDTO createEvent(EventRequestDTO dto, List<MultipartFile> photos, List<MultipartFile> guestPhotos);

    EventResponseDTO updateEvent(Long id, EventRequestDTO dto,List<MultipartFile> photos);

    EventResponseDTO createEventFromJson(String eventJson, List<MultipartFile> photos, List<MultipartFile> guestPhotos);

    EventResponseDTO updateEventFromJson(Long id, String eventJson, List<MultipartFile> photos);

    String deleteEvent(Long id);
}
