package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import sub.db.entity.Operator;

public interface OperatorRepository  extends CrudRepository<Operator,Long> {
}
