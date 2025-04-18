package az.edu.turingacademybackend.mapper;


import az.edu.turingacademybackend.dto.request.FaqItemDto;
import az.edu.turingacademybackend.dto.request.ScholarshipRequestDto;
import az.edu.turingacademybackend.dto.request.StepDto;
import az.edu.turingacademybackend.dto.response.ScholarshipResponseDto;
import az.edu.turingacademybackend.entity.FaqItemEntity;
import az.edu.turingacademybackend.entity.ScholarshipEntity;
import az.edu.turingacademybackend.entity.StepEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {

    @Mapping(target = "id", ignore = true)
    ScholarshipEntity toEntity(ScholarshipRequestDto dto);

    ScholarshipResponseDto toResponseDto(ScholarshipEntity entity);

    @Mapping(target = "id", ignore = true)
    StepEntity toStepEntity(StepDto dto);
    StepDto toStepDto(StepEntity entity);

    @Mapping(target = "id", ignore = true)
    FaqItemEntity toFaqItemEntity(FaqItemDto dto);
    FaqItemDto toFaqItemDto(FaqItemEntity entity);

    List<StepEntity> toStepEntityList(List<StepDto> dto);
    List<StepDto> toStepDtoList(List<StepEntity> entity);

    List<FaqItemEntity> toFaqItemEntityList(List<FaqItemDto> dto);
    List<FaqItemDto> toFaqItemDtoList(List<FaqItemEntity> entity);
}
