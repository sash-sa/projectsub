package sub.web.services.issue;

import sub.db.dto.IssueDto;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;

import java.util.List;
import java.util.Map;

public interface IssueService {
    public List<Issue> get();
    public IssueDto get(Long id);
    public Issue create(Issue issue);
    public Issue update(Issue issue);
    public Boolean delete(Long id);
    public Map<String,List<Issue>> getIssueForStatus();
}
