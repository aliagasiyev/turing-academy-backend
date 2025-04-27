package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.GraduateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduateRepository extends JpaRepository<GraduateEntity, Long> {

    boolean existsByStudentName(String studentName);
}
