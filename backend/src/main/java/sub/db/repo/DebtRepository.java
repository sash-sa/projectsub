package sub.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import sub.db.entity.Debt;

import javax.annotation.Resource;

@Component
public interface DebtRepository extends CrudRepository<Debt,Long> {
}
