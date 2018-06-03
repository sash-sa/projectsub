package sub.web.services.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sub.db.entity.Configuration;
import sub.db.entity.IssueStatus;
import sub.db.repo.ConfigurationRepository;

import java.util.List;

@Component
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public Configuration get() {
        return getConfiguration();
    }

    @Override
    public Configuration edit(Configuration configuration) {
        return configurationRepository.save(configuration);
    }


    @Override
    public IssueStatus getIssueStatusStart() {
        Configuration configuration = getConfiguration();
        return configuration.getIssueStatusStart();
    }

    @Override
    public IssueStatus setIssueStatusStart(IssueStatus issueStatus) {
        Configuration configuration = getConfiguration();
        configuration.setIssueStatusStart(issueStatus);
        configurationRepository.save(configuration);
        return configuration.getIssueStatusClosed();
    }

    @Override
    public Configuration getConfiguration() {
        List<Configuration> configurations = configurationRepository.findAll();
        if (!configurations.isEmpty()) {
            return configurations.get(0);
        }
        return configurationRepository.save(new Configuration());
    }
}
