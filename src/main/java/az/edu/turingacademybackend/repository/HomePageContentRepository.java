package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.HomePageContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HomePageContentRepository extends JpaRepository<HomePageContentEntity, Long> {

    // HomePageContent-ın ilk qeydi alınır
    Optional<HomePageContentEntity> findFirstByOrderByIdAsc();
}
