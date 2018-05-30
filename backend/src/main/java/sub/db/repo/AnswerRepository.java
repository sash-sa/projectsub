package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sub.db.entity.Answer;

@Component
public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
