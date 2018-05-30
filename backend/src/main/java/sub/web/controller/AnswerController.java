package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sub.web.services.answer.AnswerService;

@Component
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;


}
