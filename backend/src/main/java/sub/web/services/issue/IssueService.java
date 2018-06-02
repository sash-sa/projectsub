package sub.web.services.issue;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import sub.db.dto.IssueDto;
import sub.db.dto.IssueFullDto;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;
import sub.db.entity.User;

import java.util.List;
import java.util.Map;

public interface IssueService {
    public List<IssueFullDto> get();
    public IssueDto get(Long id);
    public Issue create(OAuth2Authentication authentication, Issue issue);
    public Issue update(OAuth2Authentication authentication, Issue issue);
    public Boolean delete(Long id);
    public Map<String,List<Issue>> getIssueForStatus();
}
