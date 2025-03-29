package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.EventRequestDTO;
import az.edu.turingacademybackend.dto.response.EventResponseDTO;
import az.edu.turingacademybackend.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GuestMapper.class)
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(dto.getEventDate().isAfter(java.time.LocalDateTime.now()) ? az.edu.turingacademybackend.enums.EventStatus.FUTURE : az.edu.turingacademybackend.enums.EventStatus.PAST)")
    EventEntity toEntity(EventRequestDTO dto);

    @Mapping(target = "status", expression = "java(event.getEventDate().isAfter(java.time.LocalDateTime.now()) ? \"FUTURE\" : \"PAST\")")
    EventResponseDTO toDto(EventEntity event);
}
