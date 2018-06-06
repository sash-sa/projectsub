package sub.db.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sub.db.dto.IssueDto;
import sub.db.dto.IssueFullDto;
import sub.db.entity.Issue;

import java.util.Date;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    @EntityGraph(attributePaths = {"issueStatus","worker","user"})
    @Query("select issue from Issue issue order by issue.id")
    List<IssueFullDto> all();


    @EntityGraph(attributePaths = {"issueStatus","worker"})
    @Query("select issue from Issue issue where issue.id= :id")
    IssueFullDto getUniq(@Param("id") Long aLong);

    @EntityGraph(attributePaths = {"worker"})
    @Query("select issue from Issue issue where issue.issueStatus.id= :status ")
    List<IssueDto> findIssueForStatus(@Param("status") Long status);

    @EntityGraph(attributePaths = {"worker"})
    @Query("select issue from Issue  issue where issue.worker.id= :worker and issue.timeExecution is not null order by issue.timeExecution desc ")
    List<IssueFullDto> findDateToWorker(@Param("worker") Long worker);
}
