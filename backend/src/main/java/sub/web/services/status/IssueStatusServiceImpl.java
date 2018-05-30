package sub.web.services.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;
import sub.db.repo.IssueStatusRepository;

import java.util.List;

@Service
public class IssueStatusServiceImpl implements IssueStatusService {
    @Autowired
    private IssueStatusRepository issueStatusRepository;

    @Override
    public List<IssueStatus> get() {
        return (List<IssueStatus>) issueStatusRepository.findAll();
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
