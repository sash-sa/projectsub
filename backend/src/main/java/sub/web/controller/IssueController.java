package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.Issue;
import sub.web.services.issue.IssueService;

@Component
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"}, maxAge = 3000)
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get() {
        return new ResponseEntity(issueService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public ResponseEntity getIssueForStatus() {
        return new ResponseEntity(issueService.getIssueForStatus(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity getIssue(@PathVariable("id") Long id) {
        return new ResponseEntity(issueService.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(OAuth2Authentication authentication,@RequestBody Issue issue) {
        return new ResponseEntity(issueService.create(authentication,issue), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(OAuth2Authentication authentication,@RequestBody Issue issue) {
        return new ResponseEntity(issueService.update(authentication,issue), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return new ResponseEntity(issueService.delete(id) == true ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }


}