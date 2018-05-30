package sub.web.services.worker;

import sub.db.entity.Worker;

import java.util.List;

public interface WorkerService {
    public List<Worker> get();
    public Worker save(Worker worker);
    public Boolean delete(Long id);
}
