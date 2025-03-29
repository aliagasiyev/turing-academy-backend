package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.SpecialtyEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {
    boolean existsByTitleIgnoreCase(@NotBlank(message = "Title is required") String title);
}
