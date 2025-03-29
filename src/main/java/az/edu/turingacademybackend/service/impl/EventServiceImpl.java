package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.EventRequestDTO;
import az.edu.turingacademybackend.dto.response.EventResponseDTO;
import az.edu.turingacademybackend.entity.EventEntity;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.EventMapper;
import az.edu.turingacademybackend.repository.EventRepository;
import az.edu.turingacademybackend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventResponseDTO> getAllEvents(Integer limit) {
        List<EventEntity> events = (limit == null)
                ? eventRepository.findAll()
                : eventRepository.findAll().stream().limit(limit).toList();

        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return eventMapper.toDto(entity);
    }

    @Override
    public EventResponseDTO createEvent(EventRequestDTO dto) {
        try {
            validateBusinessLogic(dto);

            EventEntity entity = eventMapper.toEntity(dto);
            EventEntity saved = eventRepository.save(entity);
            return eventMapper.toDto(saved);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violated while creating event: " + extractRootMessage(e));
        }
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO dto) {
        eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        try {
            validateBusinessLogic(dto);

            EventEntity updated = eventMapper.toEntity(dto);
            updated.setId(id);

            EventEntity saved = eventRepository.save(updated);
            return eventMapper.toDto(saved);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violated while updating event: " + extractRootMessage(e));
        }
    }

    @Override
    public void deleteEvent(Long id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        try {
            eventRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to delete event: " + extractRootMessage(e));
        }
    }

    private void validateBusinessLogic(EventRequestDTO dto) {
        if (dto.getEventDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event date cannot be in the past");
        }
        if (dto.getRegisterDeadline().isAfter(dto.getEventDate())) {
            throw new IllegalArgumentException("Register deadline must be before event date");
        }
    }

    private String extractRootMessage(DataIntegrityViolationException e) {
        return (e.getRootCause() != null) ? e.getRootCause().getMessage() : e.getMessage();
    }
}
