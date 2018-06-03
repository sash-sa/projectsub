package sub.web.services.configuration;

import sub.db.entity.Configuration;
import sub.db.entity.IssueStatus;

public interface ConfigurationService {
    Configuration get();
    Configuration edit(Configuration configuration);
    IssueStatus getIssueStatusStart();
    IssueStatus setIssueStatusStart(IssueStatus issueStatus);
    Configuration getConfiguration();
}
