package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import sub.db.entity.ImportConfiguration;

@Component
public interface ImportConfigurationRepository  extends CrudRepository<ImportConfiguration,String> {
}
