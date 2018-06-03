package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sub.db.entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration,Long> {
}
