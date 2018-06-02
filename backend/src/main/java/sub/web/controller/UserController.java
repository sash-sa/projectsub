package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.User;
import sub.web.services.operator.UserService;

@Component
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"}, maxAge = 3000)
@RequestMapping("/api/operator")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAuthenticatedUserInformation(OAuth2Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInfo(authentication));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity get() {
        return new ResponseEntity(userService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody User operator) {
        return new ResponseEntity(userService.save(operator), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody User operator) {
        return new ResponseEntity(userService.update(operator), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(userService.delete(id)==true?HttpStatus.OK:HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity getRole() {
        return new ResponseEntity(userService.getRole(), HttpStatus.OK);
    }
}
