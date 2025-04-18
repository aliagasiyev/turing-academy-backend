package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.EventRequestDTO;
import az.edu.turingacademybackend.dto.response.EventResponseDTO;
import az.edu.turingacademybackend.entity.EventEntity;
import az.edu.turingacademybackend.exceptions.DuplicateResourceException;
import az.edu.turingacademybackend.exceptions.InvalidRequestException;
import az.edu.turingacademybackend.exceptions.ResourceNotFoundException;
import az.edu.turingacademybackend.mapper.EventMapper;
import az.edu.turingacademybackend.repository.EventRepository;
import az.edu.turingacademybackend.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<EventResponseDTO> getAllEvents(Integer limit) {
        List<EventEntity> events = (limit == null)
                ? eventRepository.findAll()
                : eventRepository.findAll().stream().limit(limit).toList();

        if (events.isEmpty()) {
            throw new ResourceNotFoundException("No events found");
        }

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
    public EventResponseDTO createEvent(EventRequestDTO dto, List<MultipartFile> photos, List<MultipartFile> guestPhotos) {
        checkDuplicateEvent(dto);
        validateDates(dto);

        List<String> eventPhotoUrls = saveUploadedPhotos(photos);
        dto.setPhotos(eventPhotoUrls);

        for (int i = 0; i < Math.min(dto.getGuests().size(), guestPhotos.size()); i++) {
            MultipartFile guestPhoto = guestPhotos.get(i);
            String guestUrl = saveGuestPhoto(guestPhoto);
            dto.getGuests().get(i).setPhotoUrl(guestUrl);
        }
        return buildAndSaveEvent(dto);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO dto, List<MultipartFile> photos) {
        eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        validateDates(dto);

        if (photos != null && !photos.isEmpty()) {
            List<String> photoUrls = saveUploadedPhotos(photos);
            dto.setPhotos(photoUrls);
        }

        try {
            EventEntity updated = eventMapper.toEntity(dto);
            updated.setId(id);
            EventEntity saved = eventRepository.save(updated);
            return eventMapper.toDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violated while updating event: " + extractRootMessage(e));
        }
    }

    @Override
    public String deleteEvent(Long id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        try {
            eventRepository.delete(entity);
            return "Event deleted successfully";
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Unable to delete event: " + extractRootMessage(e));
        }
    }

    @Override
    public EventResponseDTO createEventFromJson(String eventJson, List<MultipartFile> photos, List<MultipartFile> guestPhotos) {
        try {
            EventRequestDTO dto = objectMapper.readValue(eventJson, EventRequestDTO.class);
            return createEvent(dto, photos, guestPhotos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for EventRequestDTO", e);
        }
    }

    @Override
    public EventResponseDTO updateEventFromJson(Long id, String eventJson, List<MultipartFile> photos) {
        try {
            EventRequestDTO dto = objectMapper.readValue(eventJson, EventRequestDTO.class);
            return updateEvent(id, dto, photos);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for EventRequestDTO", e);
        }
    }
    private List<String> saveUploadedPhotos(List<MultipartFile> photos) {
        return photos.stream().map(photo -> {
            try {
                String basePath = System.getProperty("user.dir");
                String uploadDir = basePath + "/uploads/events";

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
                String filePath = uploadDir + "/" + fileName;
                photo.transferTo(new File(filePath));

                return "/uploads/events/" + fileName;
            } catch (Exception ex) {
                throw new RuntimeException("Photo upload failed: " + ex.getMessage());
            }
        }).toList();
    }

    private String saveGuestPhoto(MultipartFile guestPhoto) {
        try {
            String basePath = System.getProperty("user.dir");
            String uploadDir = basePath + "/uploads/guests";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + guestPhoto.getOriginalFilename();
            String filePath = uploadDir + "/" + fileName;
            guestPhoto.transferTo(new File(filePath));

            return "/uploads/guests/" + fileName;
        } catch (Exception ex) {
            throw new RuntimeException("Guest photo upload failed: " + ex.getMessage());
        }
    }

    private EventResponseDTO buildAndSaveEvent(EventRequestDTO dto) {
        EventEntity entity = eventMapper.toEntity(dto);
        EventEntity saved = eventRepository.save(entity);
        return eventMapper.toDto(saved);
    }

    private void checkDuplicateEvent(EventRequestDTO dto) {
        eventRepository.findFirstByNameIgnoreCaseAndEventDate(dto.getName(), dto.getEventDate())
                .ifPresent(e -> {
                    throw new DuplicateResourceException("An event with the same name and date already exists");
                });
    }

    private void validateDates(EventRequestDTO dto) {
        if (dto.getRegisterDeadline().isBefore(dto.getEventDate())) {
            throw new InvalidRequestException("Register deadline must be before the event date");
        }
        if (dto.getRegisterDeadline().isBefore(LocalDateTime.now())) {
            throw new InvalidRequestException("Register deadline cannot be in the past");
        }
    }

    private String extractRootMessage(DataIntegrityViolationException e) {
        return (e.getRootCause() != null) ? e.getRootCause().getMessage() : e.getMessage();
    }
}
