package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.HomePageContentRequest;
import az.edu.turingacademybackend.dto.response.HomePageContentResponse;
import az.edu.turingacademybackend.entity.HomePageContentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PartnerMapper.class)
public interface HomePageContentMapper {

    @Mapping(target = "partners", source = "partners")
    HomePageContentEntity toEntity(HomePageContentRequest request);

    @Mapping(target = "partners", source = "partners")
    HomePageContentResponse toResponse(HomePageContentEntity entity);
}
