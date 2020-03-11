package medicalimagebrowser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import medicalimagebrowser.entity.Instance;

import java.util.Optional;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {

    Optional<Instance> findBySOPInstanceUID(String SOPInstanceUID);

}
