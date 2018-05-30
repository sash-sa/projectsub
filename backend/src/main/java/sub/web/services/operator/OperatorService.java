package sub.web.services.operator;

import org.springframework.stereotype.Component;
import sub.db.entity.Operator;

import java.util.List;

@Component
public interface OperatorService {
    public List<Operator>get();
    public Operator get(Long id);
    public Operator save(Operator operator);
    public Operator delete(Operator operator);
}
