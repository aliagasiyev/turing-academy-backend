package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.CommunityCardRequest;
import az.edu.turingacademybackend.dto.response.CommunityCardResponse;
import az.edu.turingacademybackend.entity.CommunityCardEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommunityCardMapper {

    CommunityCardResponse toResponse(CommunityCardEntity entity);

    CommunityCardEntity toEntity(CommunityCardRequest request);

    List<CommunityCardResponse> toResponseList(List<CommunityCardEntity> entities);

    List<CommunityCardEntity> toEntityList(List<CommunityCardRequest> requests);
}
