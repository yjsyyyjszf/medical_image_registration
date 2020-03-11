package medicalimagebrowser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import medicalimagebrowser.entity.Study;

import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByStudyID(String studyID);

}
