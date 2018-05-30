package sub.db.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sub.db.dto.IssueDto;
import sub.db.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    @EntityGraph(attributePaths = {"issueStatus","worker"})
    @Query("select issue from Issue issue order by issue.id")
    List<Issue> findAll();


    @EntityGraph(attributePaths = {"issueStatus","worker"})
    @Query("select issue from Issue issue where issue.id= :id")
    IssueDto getUniq(@Param("id") Long aLong);

    @EntityGraph(attributePaths = {"worker"})
    @Query("select issue from Issue issue where issue.issueStatus.id= :status ")
    List<Issue> findIssueForStatus(@Param("status") Long status);
}
