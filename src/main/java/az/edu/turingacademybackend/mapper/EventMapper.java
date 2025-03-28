package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.EventRequestDTO;
import az.edu.turingacademybackend.dto.EventResponseDTO;
import az.edu.turingacademybackend.entity.EventEntity;
import az.edu.turingacademybackend.enums.EventStatus;
import org.mapstruct.*;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = GuestMapper.class)
public interface EventMapper {

    EventEntity toEntity(EventRequestDTO dto);

    EventResponseDTO toDto(EventEntity entity);

    @AfterMapping
    default void setStatus(@MappingTarget EventEntity entity, EventRequestDTO dto) {
        entity.setStatus(dto.getEventDate().isAfter(LocalDateTime.now()) ? EventStatus.FUTURE : EventStatus.PAST);
    }
}
