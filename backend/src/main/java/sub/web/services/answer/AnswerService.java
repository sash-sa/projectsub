package sub.web.services.answer;

import sub.db.entity.Answer;

import java.util.Date;
import java.util.List;

public interface AnswerService {
    public List<Answer> get();
    public Answer create(String text, Date date);
    public Answer update(Long id, String text, Date date);
    public Boolean delete(Long id);

}
