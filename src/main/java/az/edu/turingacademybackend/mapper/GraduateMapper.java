package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.GraduateRequest;
import az.edu.turingacademybackend.dto.response.GraduateResponse;
import az.edu.turingacademybackend.entity.GraduateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GraduateMapper {

    GraduateEntity toEntity(GraduateRequest request);

    GraduateResponse toDto(GraduateEntity graduate);

    List<GraduateResponse> toDtoList(List<GraduateEntity> graduates);

    void updateGraduateFromRequest(GraduateRequest request, @MappingTarget GraduateEntity graduate);
}
