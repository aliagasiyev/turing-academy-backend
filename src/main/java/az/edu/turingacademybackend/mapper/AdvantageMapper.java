package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.AdvantageRequest;
import az.edu.turingacademybackend.dto.response.AdvantageResponse;
import az.edu.turingacademybackend.entity.AdvantageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvantageMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    AdvantageResponse toResponse(AdvantageEntity entity);

    AdvantageEntity toEntity(AdvantageRequest request);

    List<AdvantageResponse> toResponseList(List<AdvantageEntity> entities);

    List<AdvantageEntity> toEntityList(List<AdvantageRequest> requests);
}
