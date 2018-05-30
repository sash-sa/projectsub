package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sub.db.entity.Question;

@Component
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
