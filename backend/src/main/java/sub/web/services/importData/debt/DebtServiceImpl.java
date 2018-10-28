package sub.web.services.importData.debt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sub.db.entity.Debt;
import sub.db.repo.DebtRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DebtServiceImpl implements DebtService {
    @Autowired
    private DebtRepository debtRepository;

    @Override
    public Debt save(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public Debt update(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public void delete(Debt debt) {
        debtRepository.delete(debt);
    }

    @Override
    public Debt get(Long x) {
        return debtRepository.findById(x).get();
    }

    @Override
    public List<Debt> getAll() {
        return (ArrayList<Debt>)debtRepository.findAll();
    }
}
