package sub.web.services.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import sub.db.dto.IssueDto;
import sub.db.dto.IssueFullDto;
import sub.db.entity.Configuration;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;
import sub.db.repo.ConfigurationRepository;
import sub.db.repo.IssueRepository;
import sub.db.repo.IssueStatusRepository;
import sub.web.services.operator.UserService;

import java.util.*;

@Component
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private IssueStatusRepository issueStatusRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<IssueFullDto> get() {
        return issueRepository.all();
    }

    @Override
    public IssueDto get(Long id) {
        return issueRepository.getUniq(id);
    }

    @Override
    public Issue create(OAuth2Authentication authentication,Issue issue) {
        issue.setCreated(new Date());
        List<Configuration> configurations = (List) configurationRepository.findAll();
        issue.setIssueStatus(configurations.size() > 0 ? configurations.get(0).getIssueStatus() : null);
        issue.setUser(userService.getUserAuth(authentication));
        return issueRepository.saveAndFlush(issue);
    }

    @Override
    public Issue update(OAuth2Authentication authentication,Issue issue) {
        issue.setUpdate(new Date());
        issue.setUser(userService.getUserAuth(authentication));
        issueRepository.save(issue);
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Issue issue = issueRepository.findById(id).get();
        if (issue != null) {
            issueRepository.delete(issue);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, List<Issue>> getIssueForStatus() {
        Map<String, List<Issue>> issueMap = new LinkedHashMap<>();
        List<IssueStatus> issueStatuses = (List) issueStatusRepository.findAll();
        for (IssueStatus issueStatus : issueStatuses) {
            List<Issue> issues=new ArrayList(issueRepository.findIssueForStatus(issueStatus.getId()));
            issueMap.put(issueStatus.getName(),issues.size()<5?issues:issues.subList(0,5));
        }
        return issueMap;
    }
}
