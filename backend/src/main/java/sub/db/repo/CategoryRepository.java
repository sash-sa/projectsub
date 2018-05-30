package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sub.db.entity.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
