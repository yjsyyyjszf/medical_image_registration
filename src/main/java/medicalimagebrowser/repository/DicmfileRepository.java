package medicalimagebrowser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import medicalimagebrowser.entity.Dicmfile;

public interface DicmfileRepository extends JpaRepository<Dicmfile,Integer> {
}
