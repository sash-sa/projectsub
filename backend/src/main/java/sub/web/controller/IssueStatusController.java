package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.IssueStatus;
import sub.web.services.status.IssueStatusService;

@Component
@CrossOrigin(origins = {"http://91.203.194.42:8088","*"}, maxAge = 3000)
@RequestMapping("/api/status")
public class IssueStatusController {
    @Autowired
    private IssueStatusService issueStatusService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(){
        return new ResponseEntity(issueStatusService.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/index")
    public ResponseEntity getIndex(){
        return new ResponseEntity(issueStatusService.getForIndex(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/notStart")
    public ResponseEntity getNotStart(){
        return new ResponseEntity(issueStatusService.getNotStart(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity created(@RequestBody IssueStatus issueStatus){
        return new ResponseEntity(issueStatusService.created(issueStatus),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody IssueStatus issueStatus){
        return new ResponseEntity(issueStatusService.update(issueStatus),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}/{nomer}")
    public ResponseEntity update(@PathVariable("id")Long id,@PathVariable("nomer") Integer nomer){
        return new ResponseEntity(issueStatusService.update(id,nomer),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return new ResponseEntity(issueStatusService.deleted(id)==true?HttpStatus.OK:HttpStatus.NO_CONTENT);
    }
}
