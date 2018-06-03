package sub.web.services.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;
import sub.db.repo.IssueStatusRepository;
import sub.web.services.configuration.ConfigurationService;

import java.util.List;

@Service
public class IssueStatusServiceImpl implements IssueStatusService {
    @Autowired
    private IssueStatusRepository issueStatusRepository;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public List<IssueStatus> get() {
        return (List<IssueStatus>) issueStatusRepository.findAll();
    }

    @Override
    public List<IssueStatus> getNotStart() {
        List<IssueStatus>all= (List<IssueStatus>) issueStatusRepository.findAll();
        if(configurationService.getConfiguration().getIssueStatusStart()!=null){
            all.remove(configurationService.getConfiguration().getIssueStatusStart());
        }
        return all;
    }

    @Override
    public IssueStatus created(IssueStatus issueStatus) {
        return issueStatusRepository.save(issueStatus);
    }

    @Override
    public IssueStatus update(IssueStatus issueStatus) {
        issueStatusRepository.save(issueStatus);
        return issueStatus;
    }

    @Override
    public Boolean deleted(Long id) {
        IssueStatus issueStatus=issueStatusRepository.findById(id).get();
        if(issueStatus!=null){
            issueStatusRepository.delete(issueStatus);
            return true;
        }
        return false;
    }
}
