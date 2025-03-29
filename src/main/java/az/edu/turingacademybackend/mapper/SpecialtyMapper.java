package az.edu.turingacademybackend.mapper;


import az.edu.turingacademybackend.dto.request.*;
import az.edu.turingacademybackend.dto.response.SpecialtyResponseDto;
import az.edu.turingacademybackend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    @Mapping(target = "id", ignore = true)
    SpecialtyEntity toEntity(SpecialtyRequestDto dto);

    SpecialtyResponseDto toDto(SpecialtyEntity entity);

    @Mapping(target = "id", ignore = true)
    ModuleEntity toEntity(ModuleDto dto);
    ModuleDto toDto(ModuleEntity entity);

    @Mapping(target = "id", ignore = true)
    TeacherEntity toEntity(TeacherDto dto);
    TeacherDto toDto(TeacherEntity entity);

    @Mapping(target = "id", ignore = true)
    GraduateEntity toEntity(GraduateDto dto);
    GraduateDto toDto(GraduateEntity entity);

    FaqEntity toEntity(FaqDto dto);
    FaqDto toDto(FaqEntity entity);

    List<ModuleDto> toModuleDtoList(List<ModuleEntity> entities);
    List<ModuleEntity> toModuleEntityList(List<ModuleDto> dtos);

    List<TeacherDto> toTeacherDtoList(List<TeacherEntity> entities);
    List<TeacherEntity> toTeacherEntityList(List<TeacherDto> dtos);

    List<GraduateDto> toGraduateDtoList(List<GraduateEntity> entities);
    List<GraduateEntity> toGraduateEntityList(List<GraduateDto> dtos);
}
