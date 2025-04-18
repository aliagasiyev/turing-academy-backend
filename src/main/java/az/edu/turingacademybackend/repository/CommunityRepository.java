package az.edu.turingacademybackend.repository;

import az.edu.turingacademybackend.entity.CommunityContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityContentEntity, Long> {

    Optional<CommunityContentEntity> findFirstByOrderByIdAsc();
}
