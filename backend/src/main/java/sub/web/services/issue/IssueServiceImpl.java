package sub.web.services.issue;

import freemarker.cache.TemplateLoader;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sub.db.dto.IssueDto;
import sub.db.dto.IssueFullDto;
import sub.db.entity.Configuration;
import sub.db.entity.Issue;
import sub.db.entity.IssueStatus;
import sub.db.repo.ConfigurationRepository;
import sub.db.repo.IssueRepository;
import sub.db.repo.IssueStatusRepository;
import sub.db.repo.WorkerRepository;
import sub.web.services.configuration.ConfigurationService;
import sub.web.services.operator.UserService;

import java.io.*;
import java.util.*;

@Component
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private IssueStatusRepository issueStatusRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<IssueFullDto> get() {
        return issueRepository.all();
    }

    @Override
    public IssueFullDto get(Long id) {
        return issueRepository.getUniq(id);
    }

    @Override
    public Issue create(OAuth2Authentication authentication,Issue issue) {
        issue.setCreated(new Date());
        List<Configuration> configurations = (List) configurationRepository.findAll();
        issue.setIssueStatus(configurations.size() > 0 ? configurations.get(0).getIssueStatusStart() : null);
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
    public Issue createDecision(OAuth2Authentication authentication, Long id, String decision) {
        Issue issue=issueRepository.findById(id).get();
        issue.setDecision(decision);
        issue.setIssueStatus(configurationService.get().getIssueStatusClosed());
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(OAuth2Authentication authentication, Long id, Long status) {
        Issue issue=issueRepository.findById(id).get();
        issue.setIssueStatus(issueStatusRepository.findById(status).get());
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateWorker(OAuth2Authentication authentication, Long id, Long worker) {
        Issue issue=issueRepository.findById(id).get();
        issue.setWorker(workerRepository.findById(worker).get());
        return issueRepository.save(issue);
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
    public Map<String, List<IssueDto>> getIssueForStatus() {
        Map<String, List<IssueDto>> issueMap = new LinkedHashMap<>();
        List<IssueStatus> issueStatuses = (List) issueStatusRepository.getStatusForIndex();
        for (IssueStatus issueStatus : issueStatuses) {
            List<IssueDto> issues=new ArrayList(issueRepository.findIssueForStatus(issueStatus.getId()));
            issueMap.put(issueStatus.getName(),issues.size()<5?issues:issues.subList(0,5));
        }
        return issueMap;
    }

    @Override
    public List<IssueFullDto> getIssueforWorker(Long worker) {
        return issueRepository.findDateToWorker(worker);
    }

    @Override
    @Transactional
    public String print(Long id) {
        Issue issue=issueRepository.findById(id).get();
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Map<String, Object> root = new HashMap<>();
        root.put("id",issue.getId());
        root.put("theme",issue.getTheme());
        root.put("description",issue.getDescription());
        root.put("status",issue.getIssueStatus()!=null?issue.getIssueStatus().getName():"");
        root.put("worker",issue.getWorker().getFullName());
        root.put("applicant",issue.getFio()+", тел. : "+issue.getPhone());
        root.put("adres",issue.getAddress());
        StringWriter out = new StringWriter();

        try {
            Template temp = cfg.getTemplate("issue_uniq_print.ftlh");
            temp.process(root, out);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.getBuffer().toString();
    }
}
