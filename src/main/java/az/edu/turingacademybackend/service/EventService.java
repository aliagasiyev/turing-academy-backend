package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.EventRequestDTO;
import az.edu.turingacademybackend.dto.EventResponseDTO;

import java.util.List;

public interface EventService {

    List<EventResponseDTO> getAllEvents(Integer limit);

    EventResponseDTO getEventById(Long id);

    EventResponseDTO createEvent(EventRequestDTO dto);

    EventResponseDTO updateEvent(Long id, EventRequestDTO dto);

    void deleteEvent(Long id);
}
