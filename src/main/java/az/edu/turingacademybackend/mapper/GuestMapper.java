package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.GuestDTO;
import az.edu.turingacademybackend.entity.GuestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface GuestMapper {

    @Mapping(target = "id", ignore = true)
    GuestEntity toEntity(GuestDTO dto);

    GuestDTO toDto(GuestEntity entity);
}
