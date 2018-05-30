package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import sub.db.entity.Configuration;

public interface ConfigurationRepository extends CrudRepository<Configuration,Long> {
}
