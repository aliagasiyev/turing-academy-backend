package az.edu.turingacademybackend.mapper;

import az.edu.turingacademybackend.dto.request.GalleryRequest;
import az.edu.turingacademybackend.dto.response.GalleryResponse;
import az.edu.turingacademybackend.entity.GalleryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GalleryMapper {

    @Mapping(target = "galleryPhotos", source = "galleryPhotos")
    GalleryResponse toResponse(GalleryEntity entity);

    GalleryEntity toEntity(GalleryRequest request);
}
