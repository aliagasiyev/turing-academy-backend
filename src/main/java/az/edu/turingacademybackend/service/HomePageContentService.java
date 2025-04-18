package az.edu.turingacademybackend.service;

import az.edu.turingacademybackend.dto.request.HomePageContentRequest;
import az.edu.turingacademybackend.dto.response.HomePageContentResponse;

public interface HomePageContentService {

    HomePageContentResponse getHomePageContent();

    HomePageContentResponse updateHomePageContent(HomePageContentRequest request);
}
