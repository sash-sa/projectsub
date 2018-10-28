package sub.web.services.importData.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sub.db.repo.ImportConfigurationRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class ConfigurationImportImportServiceImpl implements ConfigurationImportService {
    @Autowired
    private ImportConfigurationRepository importConfigurationRepository;

    @Override
    public <X> X get(Integer typ) {
        return null;
    }

    @Override
    public <X> X save(X x, Integer typ) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
        switch (typ) {
            case 1: {
                DebtConfiguration debtConfiguration = (DebtConfiguration) x;
                objectOutputStream.writeObject(debtConfiguration);
                System.out.println();
//                    importConfigurationRepository.save(objectOutputStream.writeObject())
                break;
            }
        }
        return null;
    }
}
