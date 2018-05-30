package sub.web.services.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sub.db.entity.Position;
import sub.db.entity.Worker;
import sub.db.repo.PositionRepository;
import sub.db.repo.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Component
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Worker> get() {
        return (List<Worker>) workerRepository.findAll();
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Worker> worker= workerRepository.findById(id);
        if(worker.isPresent()){
            workerRepository.delete(worker.get());
            return  true;
        }
        return false;
    }
}
