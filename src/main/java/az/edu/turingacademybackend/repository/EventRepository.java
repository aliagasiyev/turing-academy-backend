package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findFirstByNameIgnoreCaseAndEventDate(String name, LocalDateTime eventDate);

}
