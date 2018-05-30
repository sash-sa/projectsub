package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import sub.db.entity.IssueStatus;

public interface IssueStatusRepository extends CrudRepository<IssueStatus,Long> {
}
