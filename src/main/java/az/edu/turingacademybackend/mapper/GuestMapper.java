package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.GuestDTO;
import az.edu.turingacademybackend.entity.GuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestEntity toEntity(GuestDTO dto);

    GuestDTO toDto(GuestEntity entity);
}
