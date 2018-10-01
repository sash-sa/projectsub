package sub.db.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sub.db.entity.IssueStatus;

import java.util.List;

public interface IssueStatusRepository extends CrudRepository<IssueStatus,Long> {
    @Query("select max(status.nomer) from IssueStatus status")
    public Integer maxNumber();

    @Query("select status from IssueStatus  status where status.visibleForIndex=true order by status.nomer")
    public List<IssueStatus> getStatusForIndex();
}
