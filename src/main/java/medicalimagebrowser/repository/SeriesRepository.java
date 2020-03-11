package medicalimagebrowser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import medicalimagebrowser.entity.Series;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findBySeriesInstanceUID(String seriesInstanceUID);

}
