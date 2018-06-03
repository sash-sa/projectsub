package sub.web.services.status;

import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;

import java.util.List;

public interface IssueStatusService {
    List<IssueStatus> get();
    List<IssueStatus> getNotStart();
    IssueStatus created(IssueStatus issueStatus);
    IssueStatus update(IssueStatus issueStatus);
    Boolean deleted(Long id);
}
