package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.CommunityRequest;
import az.edu.turingacademybackend.dto.response.CommunityResponse;
import az.edu.turingacademybackend.entity.CommunityContentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CommunityCardMapper.class,
        AdvantageMapper.class,
        GalleryMapper.class
})
public interface CommunityMapper {

    CommunityContentEntity toEntity(CommunityRequest request);

    CommunityResponse toResponse(CommunityContentEntity entity);
}
