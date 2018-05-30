package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import sub.db.entity.Worker;

public interface WorkerRepository extends CrudRepository<Worker,Long> {
}
