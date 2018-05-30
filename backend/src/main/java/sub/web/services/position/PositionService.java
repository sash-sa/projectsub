package sub.web.services.position;

import sub.db.entity.Position;

import java.util.List;

public interface PositionService {
    public List<Position> get();

    public Position save(String name,String description);

    public Position update(Long id, String name,String description);

    public Boolean delete(Long id);
}
