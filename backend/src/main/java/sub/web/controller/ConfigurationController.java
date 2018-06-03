package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sub.db.entity.Configuration;
import sub.db.entity.IssueStatus;
import sub.web.services.configuration.ConfigurationService;

@Component
@CrossOrigin(origins = {"**"}, maxAge = 3000)
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get() {
        return new ResponseEntity(configurationService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statusStart")
    public IssueStatus getIssueStatusStart() {
        return configurationService.getIssueStatusStart();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/statusStart")
    public IssueStatus setIssueStatusStart(@RequestBody IssueStatus issueStatus) {
        return configurationService.setIssueStatusStart(issueStatus);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity edit(@RequestBody Configuration configuration) {
        return new ResponseEntity(configurationService.edit(configuration), HttpStatus.OK);
    }

}
