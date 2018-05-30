package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import sub.db.entity.Position;

public interface PositionRepository extends CrudRepository<Position,Long> {
}
