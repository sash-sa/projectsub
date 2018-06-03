package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.Worker;
import sub.web.services.worker.WorkerService;

import java.util.List;

@Component
@CrossOrigin(origins = {"**"}, maxAge = 3000)
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get() {
        return new ResponseEntity(workerService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity save(@RequestBody Worker worker) {
        return new ResponseEntity(workerService.save(worker),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return new ResponseEntity(workerService.delete(id)==true?HttpStatus.OK:HttpStatus.NO_CONTENT);
    }
}
