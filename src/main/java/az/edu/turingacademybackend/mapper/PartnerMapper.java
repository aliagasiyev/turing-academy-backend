package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.PartnerRequest;
import az.edu.turingacademybackend.dto.response.PartnerResponse;
import az.edu.turingacademybackend.entity.PartnerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    PartnerEntity toEntity(PartnerRequest request);

    PartnerResponse toResponse(PartnerEntity entity);
}
