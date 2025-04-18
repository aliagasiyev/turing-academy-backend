package az.edu.turingacademybackend.service.impl;

import az.edu.turingacademybackend.dto.request.HomePageContentRequest;
import az.edu.turingacademybackend.dto.response.HomePageContentResponse;
import az.edu.turingacademybackend.entity.HomePageContentEntity;
import az.edu.turingacademybackend.mapper.HomePageContentMapper;
import az.edu.turingacademybackend.repository.HomePageContentRepository;
import az.edu.turingacademybackend.service.HomePageContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomePageContentServiceImpl implements HomePageContentService {

    private final HomePageContentRepository repository;
    private final HomePageContentMapper mapper;

    @Override
    public HomePageContentResponse getHomePageContent() {
        HomePageContentEntity entity = repository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Home Page Content not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public HomePageContentResponse updateHomePageContent(HomePageContentRequest request) {
        HomePageContentEntity entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }
}
