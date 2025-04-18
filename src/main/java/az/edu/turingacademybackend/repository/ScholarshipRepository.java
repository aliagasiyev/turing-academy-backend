package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRepository extends JpaRepository<ScholarshipEntity, Long> {
    // Custom queries əlavə etmək istəsən, burada yaza bilərsən
}
