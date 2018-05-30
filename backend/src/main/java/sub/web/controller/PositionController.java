package sub.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sub.db.entity.Position;
import sub.web.services.position.PositionService;

import java.util.List;

@Component
@CrossOrigin(origins = { "http://localhost:8080","http://localhost:8081" }, maxAge = 3000)
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getPosition() {
        return new ResponseEntity(positionService.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Position> save(@RequestParam("name") String name,@RequestParam("description")String description ){
        return new ResponseEntity(positionService.save(name,description),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        return new ResponseEntity(positionService.delete(id)==true?HttpStatus.OK:HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public ResponseEntity edit(@PathVariable("id")Long id,@RequestParam("name") String name,@RequestParam("description")String description){
        return new ResponseEntity(positionService.update(id,name,description),HttpStatus.OK);
    }
}
