package sub.web.services.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import sub.db.entity.Position;
import sub.db.repo.PositionRepository;

import java.util.List;

@Component
@CrossOrigin(origins = { "http://localhost:8080" }, maxAge = 3000)
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> get() {
        return (List<Position>) positionRepository.findAll();
    }

    @Override
    public Position save(String name,String description) {
        Position position = new Position();
        position.setName(name);
        position.setDescription(description);
        return positionRepository.save(position);
    }

    @Override
    public Position update(Long id, String name,String description) {
        Position position = positionRepository.findById(id).get();
        position.setName(name);
        position.setDescription(description);
        return positionRepository.save(position);
    }

    @Override
    public Boolean delete(Long id) {
        Position position = positionRepository.findById(id).get();
        if(position!=null){
            positionRepository.delete(position);
            return true;
        }
        return false;
    }
}
