package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.Operator;
import sub.web.services.operator.OperatorService;

@Component
@CrossOrigin(origins = { "http://localhost:8080","http://localhost:8081" }, maxAge = 3000)
@RequestMapping("/api/operator")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(){
        return new ResponseEntity(operatorService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Operator operator){
        return new ResponseEntity(operatorService.save(operator),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Operator operator){
        return new ResponseEntity(operatorService.save(operator),HttpStatus.OK);
    }
}
