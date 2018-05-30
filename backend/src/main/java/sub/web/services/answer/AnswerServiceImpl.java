package sub.web.services.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sub.db.entity.Answer;
import sub.db.repo.AnswerRepository;

import java.util.Date;
import java.util.List;

@Component
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> get() {
        return answerRepository.findAll();
    }

    @Override
    public Answer create(String text, Date date) {
        Answer answer = new Answer();
        answer.setText(text);
        answer.setDate(date);
        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Long id, String text, Date date) {
        Answer answer=answerRepository.findById(id).get();
        answer.setDate(date);
        answer.setText(text);
        return answer;
    }

    @Override
    public Boolean delete(Long id) {
        Answer answer=answerRepository.findById(id).get();
        if(answer!=null){
            answerRepository.delete(answer);
            return true;
        }
        return false;
    }
}
